package Bit106.L05.client;

import Bit106.L05.common.service.SocketConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Bob on 4/7/17.
 */
public class VehicleBuilderClient implements Runnable {
    private ServiceHandler handler;

    public VehicleBuilderClient(String serverIp, int serverPort) {
        handler = new ServiceHandler(serverIp, serverPort);
    }

    public static void main(String[] args) {
        // Read command line input to start the client.
        if (args.length < 2) {
            System.out.println("Usage: java Client <server_ip> <server_port>");
            System.exit(1);
        }

        int port = 8080;
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println(e);
        }

        VehicleBuilderClient client = new VehicleBuilderClient(args[0], port);
        new Thread(client).start();
    }

    @Override
    public void run() {
        System.out.println("Start running client...");
        handler.openSession();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String buffer = null;
        while (true) {
            // Read command from CLI.
            try {
                buffer = br.readLine();
            } catch (IOException e) {
                System.out.println("Failed to read the command");
            }

            // Handle command.
            try {
                handler.handleSession(buffer);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
