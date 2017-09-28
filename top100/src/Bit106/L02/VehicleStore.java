package Bit106.L02;



import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bob on 4/7/17.
 */
public class VehicleStore {

    // Map<owner, vehicle>
    private static Map<String, Vehicle> vehicleMap = new HashMap<>();

    public synchronized void addVehicle(Vehicle vehicle) {

        if (vehicle.getPrice() == 1) {
            // sleep 100 ms.
            new SleepUtil(100).run();
        }

        if (!vehicleMap.containsKey(vehicle.getOwner())) {
            if (vehicle.getPrice() == 0) {
                // sleep 1000 ms.
                new SleepUtil(1000).run();
            }
            vehicleMap.put(vehicle.getOwner(), vehicle);
            System.out.println(String.format("Successfully add vehicle, owner: %s, price: %d",
                    vehicle.getOwner(), vehicle.getPrice()));
        } else {
            System.out.println(String.format("Duplicated owner: %s", vehicle.getOwner()));
        }
    }
}
