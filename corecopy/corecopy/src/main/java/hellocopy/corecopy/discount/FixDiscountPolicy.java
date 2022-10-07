package hellocopy.corecopy.discount;

import hellocopy.corecopy.member.Grade;
import hellocopy.corecopy.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return discountAmount;
        }else {
            return 0;
        }
    }
}
