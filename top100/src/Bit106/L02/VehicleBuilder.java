package Bit106.L02;

//import vehicle_builder.model.Vehicle;

/**
 * Created by Bob on 4/7/17.
 */
public interface VehicleBuilder {
    void createVehicle(String owner, int price);
    void displayVehicle(String owner);
    void displayVehicle(Vehicle vehicle);
}
