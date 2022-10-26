package src.main.java;

import java.rmi.Remote;
import java.rmi.RemoteException;

@Deprecated
public interface HelloService extends Remote {
    public String echo(String input) throws RemoteException;
}
