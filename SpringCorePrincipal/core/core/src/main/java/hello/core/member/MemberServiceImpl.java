package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //가입을 하고 회원 조회하려면 MemberRepository가 필요하다.
    //DIP 위반! 추상화도 의존, 구체화도 의존
    private final MemberRepository memberRepository;

    //AppConfig에서는 new MemberServiceImpl(memberRepository())를 통해서 명시적으로 DI를 해줬으나, @Component 어노테이션을 붙이면서 DI를 명시적으로 해 줄 수 없게 되었다.
    //따라서 @Autowired를 사용해서 DI를 이루어준다.
    //인자 형에 맞는 객체를 주입해준다.
    //ac.getBean(MemberRepository.class) 처럼 행동한다고 이해하면 된다.
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
