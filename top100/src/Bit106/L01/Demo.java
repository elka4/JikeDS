package Bit106.L01;

/**
 * Created by Bob on 4/1/17.
 */
public class Demo {
    public static void main(String[] args) {
        Car.getTotalNumCars();
        final Car bobCar = new Car("Bob", "BMW");
        bobCar.start();

        Car.getTotalNumCars();

        Car claireCar = new Car("Claire", "Volve");

        bobCar.setMiles(100);
        bobCar.numTotalCars = 2;

        Car.getTotalNumCars();
    }
}
