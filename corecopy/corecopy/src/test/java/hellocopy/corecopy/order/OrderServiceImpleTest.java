package hellocopy.corecopy.order;

import hellocopy.corecopy.discount.FixDiscountPolicy;
import hellocopy.corecopy.member.Grade;
import hellocopy.corecopy.member.Member;
import hellocopy.corecopy.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImpleTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        //OrderServiceImpl의 주입 방법이 수정자 주입이라고 가정!
        //nullPointerException 에러 뜬다 :
        //memoryRepository와 discountPolicy 참조값이 없기 때문이다.
        //따라서 순수자바코드로 작성 시 누락발생!
        //의존관계가 테스크 코드에서는 보이지 않는다. -> 생성자 주입 사용하자!
        //생성자 주입 사용 시 new OrderServiceImpl() 에서 컴파일 오류 발생한다.
        //컴파일 오류는 세상에서 가장 좋은 오류!!!
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
