package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//스프링이 @Controller 어노테이션을 확인하고 MemberController 객체를 가지고 있는다.
//== 스프링 빈이 관리된다.
@Controller
public class MemberController {

    //스프링 컨테이너로부터 받아 쓰도록 코드작성 해야함.
    //new 키워드를 사용하지 않고(기능이 많지 않기에 공유하면 됨) 스프링 컨테이너에 등록시킨다.
    private final MemberService memberService;

    //@Autowired 어노테이션 : 각 상황의 타입에 맞는 스프링 컨테이너 안에 존재하는 Bean을 자동으로 주입해주게 된다.
    //MemberController가 생성이 될 때, 스프링 빈에 등록되어있는 MemberService 객체를 넣어준다. : DI
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
