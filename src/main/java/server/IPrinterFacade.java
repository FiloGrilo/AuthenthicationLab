package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPrinterFacade extends Remote {
    void createUser(String username, String password) throws RemoteException;
    boolean verifyUser(String username, String password) throws AuthenticationFailedException, RemoteException;
    void print(String username, String filename, String printer) throws RemoteException;;
    void queue (String username, String printer) throws RemoteException;;
    void topQueue(String username, String printer, Integer job) throws RemoteException;
    void start(String username) throws RemoteException;
    void stop(String username) throws RemoteException;
    void restart(String username) throws RemoteException;
    void status(String username) throws RemoteException;
    void readConfig(String username, String parameter) throws RemoteException;
    void setConfig(String username, String parameter, String value) throws RemoteException;
}
