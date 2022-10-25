package hellocopy.corecopy;

import hellocopy.corecopy.member.MemberRepository;
import hellocopy.corecopy.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


import static org.springframework.context.annotation.ComponentScan.*;

//OCP, DIP를 지킬 수 있다.
@Configuration
@ComponentScan(
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    //수동 빈 등록 vs 자동 빈 등록은 수동 빈 등록이 우선권을 가지나
    //최근 스프링 부트에서는 이러한 등록으로 인하여 발생가능한 잡기 어려운 버그를 미연에 방지하기 위하여 에러를 발생시킨다.
//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
