package hellocopy.corecopy.discount;

import hellocopy.corecopy.member.Grade;
import hellocopy.corecopy.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    //vip인 경우
    void vip_o() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //간략한 테스트이기 때문에 데이터베이스에 데이터 넣을 필요까지는 없다.

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 10% 할인이 적용되지 않아야 한다.")
    //vip가 아닌 경우
    void vip_x() {
        //given
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(0);

    }
}