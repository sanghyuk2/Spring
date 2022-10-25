package hellocopy.corecopy.discount;

import hellocopy.corecopy.member.Grade;
import hellocopy.corecopy.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    //할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라.
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return discountFixAmount;
        else
            return 0;
    }
}
