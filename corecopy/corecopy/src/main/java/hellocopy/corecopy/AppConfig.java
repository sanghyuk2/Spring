package hellocopy.corecopy;

import hellocopy.corecopy.discount.DiscountPolicy;
import hellocopy.corecopy.discount.FixDiscountPolicy;
import hellocopy.corecopy.member.MemberRepository;
import hellocopy.corecopy.member.MemberService;
import hellocopy.corecopy.member.MemberServiceImpl;
import hellocopy.corecopy.member.MemoryMemberRepository;
import hellocopy.corecopy.order.OrderService;
import hellocopy.corecopy.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memoryMemberRepository());
    }

    @Bean
    public MemberRepository memoryMemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memoryMemberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
