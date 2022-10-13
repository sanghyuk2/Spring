package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        //primitive type 대신 wrapper type 쓰는 이유 : null 이 들어갈수도 있기에 사용하였다.
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

//    @Test
//    void fieldInjectionTest() {
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        //NullPointerException이 발생한다.
    //
//        //이유는 스프링 없이 순수한 자바로 테스트 하려니 안되는것이다.
//        //즉, memberRepository, discountPolicy에 원하는 값을 넣어줄수 없다는 뜻이다.
//        //실행하기 위해서는 OrderServiceImpl에 setter를 만들어주어야한다.
//        orderService.setMemberRepository(new MemoryMemberRepository());
//        orderService.setDiscountPolicy(new FixDiscountPolicy());
//        orderService.createOrder(1L, "itemA", 10000);
//
//        //결론적으로 fieldInjection 할 경우, 테스트를 할 때 부가적으로 필요한 코드들(setter 등)이 많다.
//        //그럴바에 setterInjection을 하는것이 낫다.
//
//    }

}
