package client;

import server.AuthenticationFailedException;
import server.IPrinterFacade;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Random;

public class Client {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, AuthenticationFailedException {
        IPrinterFacade service = (IPrinterFacade) Naming.lookup("rmi://localhost:5099/printerService");
        String username = generateRandomUsername();
        service.createUser(username, "test");
        service.verifyUser(username, "test");
        service.start(username);
        service.status(username);
        service.setConfig(username, "param1", "value1");
        service.readConfig(username, "param1");
        service.print(username, "file1", "Printer1");
        service.print(username, "file2", "Printer1");
        service.print(username, "file3", "Printer1");
        service.print(username, "file3", "Printer5");
        service.queue(username, "Printer1");
        service.topQueue(username, "Printer1", 2);
        service.restart(username);
    }

    private static String generateRandomUsername() {
       return "user_"  + new Random().nextInt();
    }
}
