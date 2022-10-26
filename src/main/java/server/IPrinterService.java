package src.main.java.server;

import java.rmi.Remote;

public interface IPrinterService extends Remote {

    void createUser(String username, String password);
    IPrinter verifyUser(String username, String password) throws AuthenticationFailedException;
}
