package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@ComponentScan : @Component 붙은 클래스를 찾아 스프링빈으로 등록해준다.
@ComponentScan(
        //basePackages : 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함하여 하위 패키지를 모두 탐색한다.
//        basePackages = "hello.core.member",
        //(아무것도 지정하지 않은 상태)로는 @ComponentScan이 붙은 클래스의 package부터 시작한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
