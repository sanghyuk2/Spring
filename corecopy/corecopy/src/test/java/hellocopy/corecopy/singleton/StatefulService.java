package hellocopy.corecopy.singleton;

public class StatefulService {
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제!
    }

    //위의 코드는 price값을 공유한다는 문제점이 있기에 아래 코드를 활용하여 int값을 반환하여
    //클라이언트 코드에 존재하는 지역변수에 값을 할당하도록 하자!
//    public int order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        return price;
//    }

    public int getPrice() {
        return price;
    }
}
