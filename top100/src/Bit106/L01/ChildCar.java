package Bit106.L01;


/**
 * Created by Bob on 4/1/17.
 */
public class ChildCar extends Car {
    public ChildCar() {
        super("123", "123");
    }

    void test() {
        start();
    }

    @Override
    protected void start() {
        System.out.println("starting....");
    }
}
