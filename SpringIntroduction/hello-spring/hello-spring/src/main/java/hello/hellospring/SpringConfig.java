package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


//@ComponentScan 방식으로 자동 등록이 아닌 @Configuration을 통하여 Bean을 컨테이너에 직접 등록시켜준다.
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //아래 bean들을 컨테이너에 등록한다.
    //등록된 memberRepository를 사용하여 MemberService 생성자로 넘겨준다.

    @Bean
    public MemberService memberService() {
        //생성자에 memberReposotory()를 넣어줌으로써 인자에게 전달한다.
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //이 프로젝트의 조건 : 아직 데이터 저장소가 선정되지 않음(가상의 시나리오)
        //저장소를 임시 저장소인 memoryRepository로 선정한 후 실제 데이터베이스가 선정되었을 때 클래스 하나 바꿈으로서 나머지 코드의 변환없이 사용가능하다.
        //return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
