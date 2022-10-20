package hellocopy.corecopy.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);


        statefulService1.order("userA", 10000);
        statefulService1.order("userB", 20000);

        int price = statefulService1.getPrice();

        System.out.println("price = " + price);

        //진짜 공유필드는 조심해야 한다! 스프링 빈은 항상 무상태(stateless)로 설계하자.
        //간단히 말하자면 스프링 빈으로 등록되는 클래스는 공유가 될 수 있는 전역 변수를 사용하지 말아야 한다는 것이다.
        //https://d-memory.tistory.com/26
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
