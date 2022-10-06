package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//Ctrl + shift + t를 이용하여 클래스 테스트 케이스 만들기.
//spring이 올라올 때, MemberService객체를 @Service 어노테이션을 보고 넣어준다.
//@Service
//jpa를 쓸려면 필요하다.
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //MemberService가 등록이 될 때 스프링 컨테이너에 있는 @Autowired 어노테이션 처리된 MemoryMemberRepository 객체를 주입해준다.
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입
    //wrapper클래스 Long이다.
    public Long join(Member member) {

//        long start = System.currentTimeMillis();

//        try{
            //같은 이름의 중복 회원X
            //변수 추출하기 : Ctrl + Alt + V, 알아서 반환해줌.
            //리팩토링 : Ctrl + Alt + Shift + T
            validateDuplicateMember(member); //중복 회원 검증.

            memberRepository.save(member);
            return member.getId();
//        }finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = " + timeMs);
//        }


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
//        long start = System.currentTimeMillis();
//        try {
            return memberRepository.findAll();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("findMembers = " + timeMs);
//        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
