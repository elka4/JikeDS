package Bit106.L05.common.service;

import Bit106.L05.common.model.Vehicle;

/**
 * Created by Bob on 4/15/17.
 */
public class CreateVehicleResponse extends VehicleBuilderResponse {
    private Vehicle vehicle;

    public CreateVehicleResponse() {
        vehicle = null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
