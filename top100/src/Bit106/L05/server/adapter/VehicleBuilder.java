package Bit106.L05.server.adapter;

import Bit106.L05.common.model.Vehicle;

/**
 * Created by Bob on 4/7/17.
 */
public interface VehicleBuilder {
    Vehicle createVehicle(String owner, int price);
}
