package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //가입을 하고 회원 조회하려면 MemberRepository가 필요하다.
    //DIP 위반! 추상화도 의존, 구체화도 의존
    private final MemberRepository memberRepository;

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
}
