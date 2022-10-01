package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    //afterEach() 사용하기 위해 끌어옴.
    //아래 코드는 MemberService에서 생성한 MemoryMemberRepository 객체와 다른 객체.
    //저장소가 static이라 문제는 없지만 static이 아닐 수 있는 경우를 대비해야한다.
    //해결 방법 : MemberService 클래스에서 new MemoryMemberRepository() 사용하지말고 생성자를 사용하여 외부에서 넣어주도록 바꿈.
    MemoryMemberRepository memberRepository;

    //DI(Dependency Injection) : 클래스간의 의존관계를 스프링 컨테이너가 자동으로 연결해 주는 겻을 말한다.
    //ex) 클래스A가 클래스 B,C와 상호작용한다면 객체 A는 객체 B,C와 의존관계이다.
    //필요한 이유 :
    //MemberService입장에서는 외부에서 생성자를 통하여 데이터를 주입함.
    //풀어말하면, 클라이언트가 어떤 서비스를 사용할 것인지 지정하는 대신, 클라이언트에게 무슨 서비스를 사용할 것인지를 말해주는 것이다.
    //왜냐하면, 클라이언트마다 전용 멤버서비스를 만들 때마다 코드 생산성이 떨어지며 고객이 몰라도 되는 코드가 노출되기 때문이다.
    //따라서 프레임워크가 자동으로 객체간 의존성을 주입해준다.
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void join() {
        //given : 주어졌을 때
        Member member = new Member();
        member.setName("hello");

        //when : 실행했을 때
        Long saveId = memberService.join(member);

        //then : 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    
    //테스트는 예외케이스도 중요하다.
    @Test
    public void duplicateMemberException() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        try {
            memberService.join(member2);
            //예외가 없다면 그 자리에 fail() 메소드 호출하여 테스트를 실패로 마무리짓는다.
            //fail();
        }catch(IllegalAccessError e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        }
        */
        
        
        
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}