package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//ctrl + f12를 누르면 작성한 생성자 코드가 없더라도 생성자가 롬복에 의해 작성된 것을 확인할 수 있다.
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    //인터페이스에만 의존하게끔 한다.
    //final이 붙으면 값이 무조건 있어야 한다. 한 번 값이 정해지면 불변하다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //클라이언트 객체(OrderServiceImpl)는 직접 구현 객체를 생성하고 연결하고 실행까지 하는 다양한 역할을 담당했엇다.
    //하지만 AppConfig를 생성하면서, 구현 객체를 생성하고 담당하는 역할은 AppConfig 내부에서 담당하게끔 되었다.
    //즉, 클라이언트 객체는 실행하는 책임만 담당하게 된 것이다. == SRP 성립!
    //discountPolicy에 Fix가 들어올지 Rate가 들어올 지 모른다 == DIP 성립!
    //의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다.
    //프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다."
    //또한 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야한다.
    //AppConfig 클래스가 구체화를 다루면서, 클라이언트인 OrderServiceImpl 내부의 DiscountPolicy를 Fix로 하든 Rate로 하든 변경되는 코드가 없다. == OCP 성립!
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
