package hello.core.discount;

import hello.core.member.Member;
import org.springframework.stereotype.Component;

public interface DiscountPolicy {

    //할인 대상 금액을 return 한다.
    int discount(Member member, int price);
}
