package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        
    }

    //@Bean으로 등록된 메소드의 반환 타입이다.
    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        //스프링 컨테이너는 스프링 빈에 등록된 인스턴스 타입을 보고 판단하기에 구체 타입으로도 조회 가능하다.
        //이 방법은 좋지 않다. 왜냐하면 역할에 의존해야하는데, 아래 코드는 구현에 의존하고 있다.
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }
    
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        //NoSuchBeanDefinitionException : No bean named 'xxxxx' available
//        MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        //두 번째 인자로 넘긴 메소드를 실행하였을 때, 첫 번째 인자로 넘긴 예외가 터져야 된다는 코드이다.
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}
