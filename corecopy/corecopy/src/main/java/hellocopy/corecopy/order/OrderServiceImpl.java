package hellocopy.corecopy.order;

import hellocopy.corecopy.discount.DiscountPolicy;
import hellocopy.corecopy.discount.FixDiscountPolicy;
import hellocopy.corecopy.member.Member;
import hellocopy.corecopy.member.MemberRepository;
import hellocopy.corecopy.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    MemberRepository memberRepository = new MemoryMemberRepository();
    DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int price) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, price);

        return new Order(memberId, itemName, price, discountPrice);
    }
}
