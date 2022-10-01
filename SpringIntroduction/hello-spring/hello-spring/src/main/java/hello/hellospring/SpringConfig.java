package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@ComponentScan 방식으로 자동 등록이 아닌 @Configuration을 통하여 Bean을 컨테이너에 직접 등록시켜준다.
@Configuration
public class SpringConfig {

    //아래 bean들을 컨테이너에 등록한다.
    //등록된 memberRepository를 사용하여 MemberService 생성자로 넘겨준다.

    @Bean
    public MemberService memberService() {
        //생성자에 memberReposotory()를 넣어줌으로써 인자에게 전달한다.
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
