package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPrinterFacade extends Remote {
    void createUser(String username, char[] password) throws RemoteException;
    boolean verifyUser(String username, char[] password) throws AuthenticationFailedException, RemoteException;
    String print(String username, String filename, String printer) throws Exception;
    String queue (String username, String printer) throws AuthenticationFailedException, RemoteException;
    void topQueue(String username, String printer, Integer job) throws AuthenticationFailedException, RemoteException;
    void start(String username) throws AuthenticationFailedException, RemoteException;
    void stop(String username) throws AuthenticationFailedException, RemoteException;
    void restart(String username) throws AuthenticationFailedException, RemoteException;
    String status(String username) throws AuthenticationFailedException, RemoteException;
    String readConfig(String username, String parameter) throws AuthenticationFailedException, RemoteException;
    void setConfig(String username, String parameter, String value) throws AuthenticationFailedException, RemoteException;
}
