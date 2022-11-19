package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPrinterFacade extends Remote {
    void createUser(String username, char[] password) throws RemoteException;
    boolean verifyUser(String username, char[] password) throws AuthenticationFailedException, RemoteException;
    String print(String username, String filename, String printer) throws Exception;
    String queue (String username, String printer) throws Exception;
    void topQueue(String username, String printer, Integer job) throws Exception;
    void start(String username) throws Exception;
    void stop(String username) throws Exception;
    void restart(String username) throws Exception;
    String status(String username) throws Exception;
    String readConfig(String username, String parameter) throws Exception;
    void setConfig(String username, String parameter, String value) throws Exception;
}
