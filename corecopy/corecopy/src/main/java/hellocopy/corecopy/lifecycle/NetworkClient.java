package hellocopy.corecopy.lifecycle;

/*데이터베이스 커넥션 풀이나, 네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고, (고객 응답 보다 빠르게 가능)
애플리케이션 종료 시점에 연결을 모두 종료하는 작업을 진행하려면, 객체의 초기화와 종료 작업이 필요하다. */

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//외부 네트워크에 미리 연결하는 객체
public class NetworkClient {

    private String url;

    //생성자는 유지보수 관점에서 객체 생성과 초기화 하는 부분을 명확하게 나누는것이 좋다.
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    //연결 됐을 때 부를 수 있다고 가정
    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }


}
