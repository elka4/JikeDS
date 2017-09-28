package Bit106.L05.common.service;

import Bit106.L05.common.model.Vehicle;

/**
 * Created by Bob on 4/15/17.
 */
public interface VehicleBuilderService {
    void createVehicle(CreateVehicleRequest request) throws Exception;
}
