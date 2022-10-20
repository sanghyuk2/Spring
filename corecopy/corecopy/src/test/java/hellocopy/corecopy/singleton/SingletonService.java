package hellocopy.corecopy.singleton;

public class SingletonService {
    //static이 붙은 멤버변수는 인스턴스를 생성하지 않아도 사용할 수 있다.
    //클래스가 메모리에 올라갈때 이미 자동적으로 생성되기 때문이다.
    //가령 static 메서드를 사용하기 위해선 {클래스이름}.{스태틱메서드} 로 바로 호출하여 사용할 수 있습니다.
    //https://vaert.tistory.com/101
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
