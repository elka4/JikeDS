package Bit106.L02;

/**
 * Created by Bob on 4/7/17.
 */
public class VehicleFactory {
    public static Vehicle createVehicle(String owner, int price) {
        if (price < 100) {
            return new Bike(owner, price);
        } else {
            return new Car(owner, price);
        }
    }
}
