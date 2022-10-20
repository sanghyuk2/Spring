package hellocopy.corecopy.singleton;

import hellocopy.corecopy.AppConfig;
import hellocopy.corecopy.member.Member;
import hellocopy.corecopy.member.MemberService;
import hellocopy.corecopy.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    //메모리 낭비가 심하다.
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회 : 호출할 때 마다 객체를 생성
        //MemberServiceImpl인스턴스 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    //싱글톤 패턴 단점
    //https://velog.io/@sms8377/Structure-%EC%8B%B1%EA%B8%80%ED%86%A4-%ED%8C%A8%ED%84%B4%EA%B3%BC-%EB%AC%B8%EC%A0%9C%EC%A0%90#%EC%8B%B1%EA%B8%80%ED%86%A4%EC%9D%B4-%EC%95%88%ED%8B%B0%ED%8C%A8%ED%84%B4%EC%9D%B4%EB%9D%BC%EA%B3%A0-%EB%B6%88%EB%A6%AC%EB%8A%94-%EC%9D%B4%EC%9C%A0
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {
        //new SingletonService() 불가! : private으로 생성자를 막아놨기 때문이다.
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        //isSameAs : ==
        //isEqualto : equals 메소드 오버라이딩 해서 사용
        assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        //스프링 컨테이너는 싱글턴 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);

    }
}
