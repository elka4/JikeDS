package Bit106.L05.common.service;

/**
 * Created by Bob on 4/15/17.
 */
public class CreateVehicleRequest extends VehicleBuilderRequest {
    private String owner;
    private int price;

    public CreateVehicleRequest(String owner, int price) {
        this.owner = owner;
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
