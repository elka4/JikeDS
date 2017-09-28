package Bit106.L02;

//import vehicle_builder.model.Vehicle;
//import vehicle_builder.model.VehicleFactory;
//import vehicle_builder.service.VehicleStore;

/**
 * Created by Bob on 4/7/17.
 */
public class VehicleBuilderImpl implements VehicleBuilder {
    private VehicleStore store;

    public VehicleBuilderImpl() {
        store = new VehicleStore();
    }

    public void createVehicle(String owner, int price) {
        Vehicle vehicle = VehicleFactory.createVehicle(owner, price);
        store.addVehicle(vehicle);
    }
    public void displayVehicle(String owner) {

    }
    public void displayVehicle(Vehicle vehicle) {

    }
}
