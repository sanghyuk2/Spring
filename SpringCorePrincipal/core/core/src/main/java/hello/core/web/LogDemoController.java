package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    //MyLogger 타입의 빈이 스프링컨테이너에 있으면 찾아오는 Dependency Lookup 기능을 하는 참조변수이다.
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        //고객이 어떤 url로 요청했는지 알 수 있다.
        //요청페이지의 경로를 StringBuffer object 형으로 리턴해준다.
        String requestURL = request.getRequestURL().toString();

        //MyLogger...CGLIB가 출력. 즉, 짭퉁 출력!
        //나중에 기능을 활용할 때 마치 Provider가 동작했듯이 실제 MyLogger 찾아서 동작한다.
        System.out.println("myLogger.getClass() = " + myLogger.getClass());
        
        //myLoggerProvider.getObject() : url request를 통해 생성된 MyLogger형 빈을 컨테이너에서 가져와 반환한다.
        //myLoggerProvider.getObject() 할 때 MyLogger 생성됨. 생성될 때 init이 실행되면서 UUID 생성.
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";

    }
}
