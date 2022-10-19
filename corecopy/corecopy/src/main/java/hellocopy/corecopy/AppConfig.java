package hellocopy.corecopy;

import hellocopy.corecopy.discount.DiscountPolicy;
import hellocopy.corecopy.discount.FixDiscountPolicy;
import hellocopy.corecopy.member.MemberRepository;
import hellocopy.corecopy.member.MemberService;
import hellocopy.corecopy.member.MemberServiceImpl;
import hellocopy.corecopy.member.MemoryMemberRepository;
import hellocopy.corecopy.order.OrderService;
import hellocopy.corecopy.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
