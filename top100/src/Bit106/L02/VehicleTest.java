package Bit106.L02;

//import vehicle_builder.model.Bike;
//import vehicle_builder.model.Car;
//import vehicle_builder.model.Vehicle;
//import vehicle_builder.model.VehicleFactory;

/**
 * Created by Bob on 4/7/17.
 */
public class VehicleTest {
    public static void main(String[] args) {
        System.out.println("==== Start VehicleTest ====");
        Car bobCar = new Car("Bob", 120);
        bobCar.start();
        System.out.println(bobCar.getOwner());

        // equals() and hashCode().
        System.out.println("---- Test equals() ----");
        Car dupBobCar = new Car("Bob", 120);
        Car claireCar = new Car("Claire", 60);

        System.out.println(bobCar.equals(bobCar));
        System.out.println(bobCar.equals(dupBobCar));
        System.out.println(bobCar.equals(claireCar));

        // Polymorphism.
        System.out.println("---- Test Polymorphism ----");
        System.out.println(bobCar instanceof Car);
        System.out.println(bobCar instanceof Vehicle);
        System.out.println(bobCar instanceof Object);

        Vehicle bobVehicle = new Car("Bob", 212);
        Vehicle bobBike = new Bike("Bob", 20);

        testVehicle(bobVehicle);
        testVehicle(bobBike);

        // Factory.
        System.out.println("---- Test Factory ----");
        Vehicle vehicle = VehicleFactory.createVehicle("Bob", 1000);
        Vehicle vehicle2 = VehicleFactory.createVehicle("Bob", 50);
        testVehicle(vehicle);
        testVehicle(vehicle2);
    }

    private static void testVehicle(Vehicle v) {
        v.start();
    }
}
