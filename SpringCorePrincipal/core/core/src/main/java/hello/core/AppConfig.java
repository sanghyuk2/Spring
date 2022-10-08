package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

//배우를 지정해주는 기획자 역할을 하는 클래스이다.
public class AppConfig {

    public MemberService memberService() {
        //이렇게 함으로써 MemberServiceImpl이 더 이상 memberRespository가 MemoryMemberRepository를 참조하지 않도록 한다.
        //즉, 더 이상 MemberServiceImpl이 구현객체에 신경 안쓴다. (DIP 지킴!)
        //생성자 주입이라고 한다.

        //Ctrl + Alt + M : 메소드 추출출
       return new MemberServiceImpl(memberRepository());
    }

    //리팩터링을 함으로써 MemberRepository의 역할이 드러남.
    //동시에 memberService()와 orderService()에 동시에 사용되던 new MemoryMemberRepository를 중복제거하였다.
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //리팩터링을 함으로써 DiscountPolicy의 역할이 드러남.
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }


}
