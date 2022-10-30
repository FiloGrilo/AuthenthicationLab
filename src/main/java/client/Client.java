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
        service.createUser("fdfdf", "test");
        service.verifyUser(username, "test");
    }

    private static String generateRandomUsername() {
       return "user_"  + new Random().nextInt();
    }
}
