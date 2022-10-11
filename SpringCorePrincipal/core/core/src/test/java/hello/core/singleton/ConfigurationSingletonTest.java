package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //구체 타입으로 꺼낸 이유는 구체 클래스에 테스트 용도로 만든 메서드를 사용하기 위해서이다.
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService --> memberRepository = " + memberRepository1);
        System.out.println("orderService --> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);



    }

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //실제 반환되는 객체는 AppConfig@CGLIB 이지만 이는 AppConfig를 상속하기에 문제없다.
        AppConfig bean = ac.getBean(AppConfig.class);

        //bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$bac65005 출력
        //순수한 클래스라면 다음과 같이 출력되어야 한다.
        //class.hello.core.AppConfig
        //뒤에 CGLIB가 붙었다는 의미는 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다.
        //임의의 다른 클래스가 싱글톤이 보장되도록 해준다.

//        예상 코드
//        @Bean
//        public MemberRepository memberRepository() {
//
//            if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
//                return 스프링 컨테이너에서 찾아서 반환;
//            } else { //스프링 컨테이너에 없으면
//                기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
//                return 반환
//            }
//        }
        System.out.println("bean = " + bean.getClass());
    }
}
