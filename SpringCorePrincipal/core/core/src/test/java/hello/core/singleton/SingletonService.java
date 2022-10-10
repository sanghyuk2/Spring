package hello.core.singleton;

public class SingletonService {

    //클래스 레벨이 존재
    //클래스 변수는 해당 클래스 정보가 가상머신에 의해 읽히는 순간 메모리 공간에 할당되고 초기화 된다.
    //인스턴스의 생성 및 클래스 변수의 접근을 위해서는 먼저 해당 클래스의 바이트코드가 메모리 공간에 로딩되어야 하는데 이 때 로딩되는 메모리 공간이 '메소드 영역(= 클래스/코드 영역)'이다.
    //참조를 목적으로만 존재하는 값은 final 선언이 된 클래스 변수에 담는다.
    //궁금한 점) static 변수는 클래스 영역에 존재. 즉, 인스턴스 정보보다 먼저 클래스 로더에 의해 읽혀있는 상태이다.
    //그런데 어떻게 인스턴스 생성에 관여가능?
    //예상) '클래스 변수는 해당 클래스 정보가 가상머신에 의해 읽히는 순간 메모리 공간에 할당되고' 라는 내용에서 알 수 있다싶이, 해당 클래스의 정보(SingletonService)가 읽힐 때 instance가 할당되기에
    //읽힌 SingletonService정보를 기반으로 생성 가능하다?
    //영한 선생님 왈) JVM이 뜰 때, SingletonService의 static 영역의 오른쪽의 new를 확인하고, 내부적으로 실행하여, 객체를 생성하여 instance에 참조시킨다.
    private static final SingletonService instance = new SingletonService();

    //객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다.
    //이 메서드를 호출하면 항상 같은 인스턴스(이미 생성된 인스턴스)를 반환한다.
    public static SingletonService getInstance() {
        return instance;
    }

    //외부에서 객체 생성 불가능하게 만든다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
