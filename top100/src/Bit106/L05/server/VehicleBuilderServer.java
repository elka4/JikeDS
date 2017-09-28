package Bit106.L05.server;

import Bit106.L05.common.service.SocketConnector;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Bob on 4/15/17.
 */
public class VehicleBuilderServer implements Runnable {
    private SocketConnector connector;

    public VehicleBuilderServer(String host, int serverPort) {
        connector = new SocketConnectorImpl(host, serverPort);
    }

    public static void main(String[] args) {
        String host = null;
        int port = 8080;

        if (args.length < 1) {
            System.out.println("Usage: java Server <port>");
            System.exit(1);
        }

        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println(e);
        }

        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            System.err.println(e);
            System.exit(1);
        }

        VehicleBuilderServer server = new VehicleBuilderServer(host, port);
        new Thread(server).start();
    }

    @Override
    public void run() {
        connector.openSession();

        while(true) {
            try {
                connector.handleSession(null);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
