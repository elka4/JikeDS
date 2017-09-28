package Bit106.L05.common.service;

/**
 * Created by Bob on 4/15/17.
 */
public interface SocketConnector {
    void openSession();

    void handleSession(String input) throws Exception;

    void closeSession();
}
