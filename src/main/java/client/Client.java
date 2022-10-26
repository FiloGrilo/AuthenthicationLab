package src.main.java.client;

import src.main.java.server.AuthenticationFailedException;
import src.main.java.server.IPrinter;
import src.main.java.server.IPrinterService;
import src.main.java.server.Printer;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException{
        IPrinterService service = (IPrinterService) Naming.lookup("rmi://localhost:5099/printerService");
        try {
            IPrinter printer = service.verifyUser("test", "test1");
            printer.print("fileee", "");
        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
    }
}
