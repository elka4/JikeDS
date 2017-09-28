package Bit106.L05.common.service;

import java.io.Serializable;

/**
 * Created by Bob on 4/15/17.
 */
public class VehicleBuilderResponse implements Serializable {
    String message;

    public VehicleBuilderResponse() {
        this.message = "";
    }

    public VehicleBuilderResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
