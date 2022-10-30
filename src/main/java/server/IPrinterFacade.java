package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPrinterFacade extends Remote {
    void createUser(String username, String password) throws RemoteException;
    boolean verifyUser(String username, String password) throws AuthenticationFailedException, RemoteException;
    void print(String filename, String printer);
}
