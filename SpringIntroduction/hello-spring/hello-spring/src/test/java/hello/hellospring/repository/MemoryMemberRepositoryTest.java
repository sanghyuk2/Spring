package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

//클래스 레벨에서 테스트 돌리면 클래스 내 메소드 전부 테스트 가능.
//순서 상관없이 메소드를 테스트한다. 즉, 테스트가 끝난 이후 남은 쓰레기 데이터로 인하여 테스트 실패가 뜰 수 있다.
//따라서 테스트가 끝날 때마다 매번 clear 해줘야한다. @AfterEach 어노테이션 사용.
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //@AfterEach 어노테이션은, 메소드가 끝날 때마다 실행되게끔 한다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift + f6 : rename
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //.get()을 사용하면 Optional 에서 제네릭타입으로 바로 접근가능. 좋은 방식은 아니다.
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }



}
