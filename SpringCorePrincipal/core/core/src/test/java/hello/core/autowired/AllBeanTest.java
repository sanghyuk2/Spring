package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

//의도적으로 해당 타입의 스프링 빈이 다 필요한 경우!
//클라이언트가 할인의 종류를 선택할 수 있다고 가정
public class AllBeanTest {
    @Test
    void findAllBean() {
        //인자를 두 개 사용 시, 두 개 다 빈으로 스프링 컨테이너에 등록된다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        //스프링 컨테이너로부터 getBean을 사용하는것이다.
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {


        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        //자바 컬렉션 인터페이스에 적용되는 방식 : 아래의 Map 경우, Map <String, DiscountPolicy>의 데이터 타입을 보고 이에 걸맞게 스프링이 주입해준다.
        @Autowired
        public DiscountService(Map <String, DiscountPolicy> policyMap, List <DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            //다형성 활용!
            //fixDiscountPolicy를 key로 넣었으므로, 매칭하는 fixDiscountPolicy 인스턴스가 나온다.
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            //fixDiscount의 discount 메서드를 이용한다.
            return discountPolicy.discount(member, price);
        }
    }
}
