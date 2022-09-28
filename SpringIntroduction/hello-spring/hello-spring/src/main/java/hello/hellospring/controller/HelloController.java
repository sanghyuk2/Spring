package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // '/hello'로 들어오면 아래 메소드 실행
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; //resources의 templates의 hello를 찾아가서 랜더링하라.

    }
}
