package Bit106.L05.common.model;

/**
 * Created by Bob on 4/7/17.
 */
public class Bike extends Vehicle {

    public Bike(String owner, int price) {
        super(owner, price);
    }

    @Override
    public void start() {
        System.out.println("Bike start...");
    }
}
