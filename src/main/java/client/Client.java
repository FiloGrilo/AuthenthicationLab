package client;

import server.AuthenticationFailedException;
import server.IPrinterService;

import java.nio.charset.Charset;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Random;

public class Client {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, AuthenticationFailedException {
        IPrinterService service = (IPrinterService) Naming.lookup("rmi://localhost:5099/printerService");
        String username = generateRandomUsername();
        service.createUser(username, "test");
        service.verifyUser(username, "test");
    }

    private static String generateRandomUsername() {
       return "user_"  + new Random().nextInt();

    }
}
