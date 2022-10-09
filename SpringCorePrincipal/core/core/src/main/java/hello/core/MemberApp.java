package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
        //MemberServiceImpl constructor 실행함
//        MemberService memberService = appConfig.memberService();

        //ApplicationContext는 스프링 컨테이너로, 모든 spring bean들을 관리한다.
        //@Configuration이 붙은 AppConfig를 설정(구성)정보로 사용한다.
        //AppConfig 클래스의 환경정보를 가지고 @Bean 어노테이션이 붙은 메소드들을 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록하여 관리한다.
        //이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라고 한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //메소드 이름을 첫 번째 인자로 전달하고, 두 번째 인자는 반환 타입 클래스의 정보를 전달한다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
