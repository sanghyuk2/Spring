package hellocopy.corecopy;

import hellocopy.corecopy.member.*;
import hellocopy.corecopy.order.Order;
import hellocopy.corecopy.order.OrderService;
import hellocopy.corecopy.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        //MemberRepository 안 쓴 이유 : 클라이언트는 데이터베이스에 직접적으로 접근하면 안된다.
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        //멤버 생성 후 데이터베이스에 저장
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        //itemPrice는 DiscountPolicy를 구현하고 있는 FixDiscountPolicy의 method인 discount메서드의 인자로 들어가서 얼마 할인할 지 반환한다.
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);

    }


}
