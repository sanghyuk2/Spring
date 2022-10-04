package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//내가 생각하는 Controller의 역할 : 웹과 붙어서 자바 내부구조와 연동시켜주는 다리 역할!
@Controller
public class HelloController {

    //정적 컨텐츠
    @GetMapping("hello") // '/hello'로 들어오면 아래 메소드 실행
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; //resources의 templates의 hello를 찾아가서 랜더링하라.

    }

    //MVC : 템플릿 엔진으로 랜더링한 HTML을 view로 나타내는 방식
    @GetMapping("hello-mvc")
    //http에서 url 끝에 ?name을 붙여주어 parameter을 넘겨주면, hello-template파일을 찾아 name이라는 attribute에 받은 paramter를 넘겨준다.
    public String helloMvc(@RequestParam("name") String name, Model model) { //외부에서 파라미터를 받기 위해서는 Annotation @RequestParam을 사용한다.
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    //http body부분에 아래 데이터를 직접 넣어주겠다는 뜻.
    //즉, 위 코드와 차이점은 view가 없이 문자 그대로 받아들여짐.
    //페이지 소스 코드를 들여다보면, html tag없이 문자만 나오는 것을 확인가능하다.
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    //API 방식 : 객체를 반환.
    //데이터를 다룰 때 template보다 api방식을 사용한다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); //intelliJ에서 alt + shift + enter 치면 직접 세미콜론을 안 쳐도 대신 쳐준다.
        hello.setName(name);
        //문자 대신 객체를 넘김.
        //페이지에 json형태 (key와 value로 이루어진 데이터구조)로 나타남.
        //xml방식과 비교하여 간단함.
        return hello;
    }

    static class Hello {
        private String name;

        //Getter and Setter 단축키 : alt + insert
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
