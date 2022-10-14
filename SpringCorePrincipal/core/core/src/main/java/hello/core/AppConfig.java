package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Bean memberService --> MemoryMemberRepository()
// @Bean orderService --> MemoryMemberRepository()
//싱글톤 깨지는것 아닌가?
//순수한 자바 코드만을 보고 판단하면 접근 횟수만큼 MemoryMemberRepository 가 생길 것 같지만 @Configuration 때문에 실상은 그렇지 않다.
//@Configuration 어노테이션이 설정정보(AppConfig)의 바이트 조작된 AppConfigCGLIB를 만들어 빈으로 등록한다.
//@Bean이 붙은 메서드마다 스프링 빈의 존재유무를 판단한 후 존재 할 시 기존의 설정 코드(AppConfig)에서 반환하고, 없을 시 생성해서 스프링 빈으로 등록하는 동적인 모습을 보여준다.
//따라서 싱글톤이 깨지는 듯 보였던 아래의 코드도 @Configuration 어노테이션이 막아준다.

//예상
//call AppConfig.memberService
//call AppConfig.memberRepository
//call AppConfig.memberRepository
//call AppConfig.orderService
//call AppConfig.memberRepository

//결과
//call AppConfig.memberService
//call AppConfig.memberRepository
//call AppConfig.orderService

//배우를 지정해주는 기획자 역할을 하는 클래스이다.
//기획자는 공연 참여자인 구현 객체들을 모두 알아야한다.
//구성 영역이 담당한다.
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        //이렇게 함으로써 MemberServiceImpl이 더 이상 memberRespository가 MemoryMemberRepository를 참조하지 않도록 한다.
        //즉, 더 이상 MemberServiceImpl이 구현객체에 신경 안쓴다. (DIP 지킴!)
        //생성자 주입이라고 한다.

        //Ctrl + Alt + M : 메소드 추출출
       return new MemberServiceImpl(memberRepository());
    }

    //리팩터링을 함으로써 MemberRepository의 역할이 드러남. 이름만 보고서 어떤 역할인지 알 수 있게 됨.
    //동시에 memberService()와 orderService()에 동시에 사용되던 new MemoryMemberRepository를 중복제거하였다.
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    //리팩터링을 함으로써 DiscountPolicy의 역할이 드러남.
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
