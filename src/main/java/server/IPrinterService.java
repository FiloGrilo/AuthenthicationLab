package src.main.java.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPrinterService extends Remote {
    void createUser(String username, String password) throws RemoteException;
    IPrinter verifyUser(String username, String password) throws AuthenticationFailedException, RemoteException;
}
