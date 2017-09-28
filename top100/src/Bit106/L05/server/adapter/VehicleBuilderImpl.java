package Bit106.L05.server.adapter;

import Bit106.L05.common.model.Vehicle;
import Bit106.L05.common.model.VehicleFactory;
import Bit106.L05.common.service.CreateVehicleRequest;
import Bit106.L05.common.service.VehicleBuilderService;
import Bit106.L05.server.service.VehicleStore;

/**
 * Created by Bob on 4/7/17.
 */
public class VehicleBuilderImpl implements VehicleBuilder {
    private VehicleStore store;

    public VehicleBuilderImpl() {
        store = new VehicleStore();
    }

    @Override
    public Vehicle createVehicle(String owner, int price) {
        Vehicle vehicle = VehicleFactory.createVehicle(owner, price);
        store.addVehicle(vehicle);
        return vehicle;
    }
}
