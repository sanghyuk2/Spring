package hellocopy.corecopy;

import hellocopy.corecopy.member.Grade;
import hellocopy.corecopy.member.Member;
import hellocopy.corecopy.member.MemberService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberSerivce = ac.getBean("memberSerivce", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberSerivce.join(member);

        Member findMember = memberSerivce.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());

    }
}
