package Bit106.L05.client;

import Bit106.L05.client.exception.InternalException;
import Bit106.L05.common.service.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Bob on 4/15/17.
 */
public class ServiceHandler implements SocketConnector, VehicleBuilderService {
    private int serverPort;
    private String serverIp;
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    public ServiceHandler(String serverIp, int serverPort) {
        this.serverPort = serverPort;
        this.serverIp = serverIp;
    }

    @Override
    public void openSession() {
        try {
            socket = new Socket(serverIp, serverPort);
            writer = new ObjectOutputStream(socket.getOutputStream());
            System.out.println(String.format("Successfully connected to server %s: %d", serverIp, serverPort));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void closeSession() {
        // Inform server to close session.
        CloseSessionRequest request = new CloseSessionRequest();
        Object response = null;
        try {
            writer.writeObject(request);

            response = reader.readObject();
        } catch (Exception e) {
            System.err.println(e);
        }

        if (response instanceof CloseSessionResponse) {
            // Close session.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }

    @Override
    public void handleSession(String input) throws Exception {
        if (input == null) {
            throw new InternalException();
        }

        String[] args = input.split("\\s+");
        if (args.length == 0) {
            System.out.println("Invalid: empty command");
        }

        // create <owner> <price>
        // quit
        switch (args[0]) {
            case "create":
                CreateVehicleRequest request = new CreateVehicleRequest(args[1], Integer.parseInt(args[2]));
                createVehicle(request);
                break;
            case "quit":
                closeSession();
                System.out.println("Bye.");
                System.exit(0);
            default:
                System.err.println(String.format("Command not supported: %s", args[0]));
        }
    }

    @Override
    public void createVehicle(CreateVehicleRequest request) throws Exception {
        if (request == null || writer == null) {
            throw new Exception("Error: fail to create vehicle.");
        }

        writer.writeObject(request);
        System.out.println("Successfully sent create request to server.");

        if (reader == null) {
            reader = new ObjectInputStream(socket.getInputStream());
        }

        CreateVehicleResponse response = (CreateVehicleResponse) reader.readObject();

        // Deal with response...
        System.out.println(response.getVehicle());
    }
}
