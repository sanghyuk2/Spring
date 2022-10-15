package hello.core.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    //afterPropertiesSet : 의존관계 주입이 끝나면~~
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
    
    //@PostConstruct와 @PreDestroy의 단점은 외부 라이브러리에는 사용못한다는 점이다.
    //외부 라이브러리란 오픈소스를 의미하는데, 오픈소스는 이미 컴파일된 .class파일들을 모아둔 jar파일이기 때문에 수정이 불가하다.
    //외부 라이브러리에서 초기화메소드와 소멸전 메소드를 호출하기 위해서는 외부 라이브러리 소스 코드 위에 어노테이션을 붙여야하는데, 수정이 불가하기 때문에 사용하지 못한다.
    //따라서 이 경우에는 @Bean(initMethod = " ", destroyMethod = " ") 를 사용하자
}
