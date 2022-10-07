package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //가입을 하고 회원 조회하려면 MemberRepository가 필요하다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
