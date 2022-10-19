package hellocopy.corecopy.beanfind;

import hellocopy.corecopy.AppConfig;
import hellocopy.corecopy.member.Member;
import hellocopy.corecopy.member.MemberService;
import hellocopy.corecopy.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import
        org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.assertj.core.api.Assertions.*;

/* 스프링 컨테이너에서 스프링을 찾는 가장 기본적인 조회 방법
ac.getBean(빈이름, 타입)
ac.getBean(타입)
*/

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입만으로 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    //참고: 구체 타입으로 조회하면 변경시 유연성이 떨어진다.
    //역할과 구현을 구분하고, 클라이언트 객체는 역할에 의존하는 것이 좋다.
    //이는 테스트 코드에서도 유효하다.
    //구현체 타입을 의존하게 되면 확장에 유연하지 못하며, 유지보수 비용이 증가한다.
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회x")
    void findBeanByNameX() {
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}
