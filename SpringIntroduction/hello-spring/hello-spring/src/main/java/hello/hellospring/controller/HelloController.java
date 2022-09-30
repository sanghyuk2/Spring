package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello") // '/hello'로 들어오면 아래 메소드 실행
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; //resources의 templates의 hello를 찾아가서 랜더링하라.

    }

    @GetMapping("hello-mvc")
    //http에서 url 끝에 ?name을 붙여주어 parameter을 넘겨주면, hello-template파일을 찾아 name이라는 attribute에 받은 paramter를 넘겨준다.
    public String helloMvc(@RequestParam("name") String name, Model model) { //외부에서 파라미터를 받기 위해서는 Annotation @RequestParam을 사용한다.
        model.addAttribute("name",name);
        return "hello-template";
    }
}
