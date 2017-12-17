//package com.javacodegeeks.core.net.unknownhostexception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class SocketTimeoutExceptionExample {

    public static void main(String[] args) {

        new Thread(new SimpleServer()).start();
        new Thread(new SimpleClient()).start();

    }

    static class SimpleServer implements Runnable {

        @Override
        public void run() {

            ServerSocket serverSocket = null;
            try {

                serverSocket = new ServerSocket(3333);

                serverSocket.setSoTimeout(7000);

                while (true) {

                    Socket clientSocket = serverSocket.accept();

                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    System.out.println("Client said :" + inputReader.readLine());
                }

            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (serverSocket != null)
                        serverSocket.close();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }

    static class SimpleClient implements Runnable {

        @Override
        public void run() {

            Socket socket = null;
            try {

                Thread.sleep(3000);

                socket = new Socket("localhost", 3333);

                PrintWriter outWriter = new PrintWriter(
                        socket.getOutputStream(), true);

                outWriter.println("Hello Mr. Server!");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {
                    if (socket != null)
                        socket.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }
}
//https://examples.javacodegeeks.com/core-java/net/sockettimeoutexception/java-net-sockettimeoutexception-how-to-solve-sockettimeoutexception/

/*
Client said :Hello Mr. Server!
java.net.SocketTimeoutException: Accept timed out
	at java.net.PlainSocketImpl.socketAccept(Native Method)
	at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
	at java.net.ServerSocket.implAccept(ServerSocket.java:545)
	at java.net.ServerSocket.accept(ServerSocket.java:513)
	at SocketTimeoutExceptionExample$SimpleServer.run(SocketTimeoutExceptionExample.java:35)
	at java.lang.Thread.run(Thread.java:748)
 */