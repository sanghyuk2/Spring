package hellocopy.corecopy;

import hellocopy.corecopy.member.Grade;
import hellocopy.corecopy.member.Member;
import hellocopy.corecopy.member.MemberService;
import hellocopy.corecopy.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //스프링 사용
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        //member 객체 생성
        Member member = new Member(1L, "memberA", Grade.VIP);

        //member 객체 데이터베이스에 저장
        memberService.join(member);

        //member 객체 데이터베이스에서 찾기
        Member findMember = memberService.findMember(1L);

        System.out.println("New member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());



    }
}
