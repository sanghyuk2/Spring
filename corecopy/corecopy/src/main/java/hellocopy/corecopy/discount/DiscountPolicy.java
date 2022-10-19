package hellocopy.corecopy.discount;

import hellocopy.corecopy.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
