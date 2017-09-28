package Bit106.L05.common.service;

import java.io.Serializable;

/**
 * Created by Bob on 4/15/17.
 */
public class VehicleBuilderRequest implements Serializable {
    String message;

    public VehicleBuilderRequest() {
        message = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
