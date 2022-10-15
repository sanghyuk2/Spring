package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        //ApplicationContext 밑에 ConfigurableApplicationContext 밑에 AnnotationConfigApplicationContext
        //ConfigurableApplicationContext를 사용함으로써 .close() 메서드 사용가능
        //.close() 메서드 사용할 일이 적기 때문에 ApplicationContext 기본 인터페이스에서 제공하지 않는다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

//        @Bean(initMethod = "init", destroyMethod = "close")

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            //객체 생성 후 외부에서 값이 들어오는 경우
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
