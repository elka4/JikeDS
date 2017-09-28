package Bit106.L01;

/**
 * Created by Bob on 4/1/17.
 */
public class Car {
    static int numTotalCars = 0;
    private String owner;
    final private String model;
    private int miles;

    public Car(String owner, String model) {
        this.owner = owner;
        this.model = model;
        miles = 0;
        numTotalCars++;
    }

    public void setMiles(int miles) {
        if (miles >= this.miles) {
            this.miles = miles;
        }
    }

    protected void start() {
        System.out.println("starting....");
    }

    protected static void getTotalNumCars() {
        System.out.println("Total number of cars: " + numTotalCars);
    }

    static class Wheel {
    }
}
