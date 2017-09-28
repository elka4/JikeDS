package Bit106.L02;

//import vehicle_builder.adapter.VehicleBuilder;
//import vehicle_builder.adapter.VehicleBuilderImpl;

/**
 * Created by Bob on 4/7/17.
 */
public class VehicleUser implements Runnable {
    private int token;
    private static VehicleBuilder builder;

    public VehicleUser(int token) {
        this.token = token;
    }

    public static void main(String[] args) {
        builder = new VehicleBuilderImpl();
        //builder.createVehicle("bob", 120);
        //builder.displayVehicle("bob");
        Thread[] threads = new Thread[2];

        for (int i = 0; i < 2; i++) {
            VehicleUser user = new VehicleUser(i);
            threads[i] = new Thread(user);
            threads[i].start();
        }

        for (int i = 0; i < 2; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("aaa");
    }

    @Override
    public void run() {
        builder.createVehicle("Bob", token);
    }
}
