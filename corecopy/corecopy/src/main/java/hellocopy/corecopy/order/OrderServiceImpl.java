package hellocopy.corecopy.order;

import hellocopy.corecopy.discount.DiscountPolicy;
import hellocopy.corecopy.discount.FixDiscountPolicy;
import hellocopy.corecopy.member.Member;
import hellocopy.corecopy.member.MemberRepository;
import hellocopy.corecopy.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        //멤버 등급 확인하여 얼마 할인할지 가격 return.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
