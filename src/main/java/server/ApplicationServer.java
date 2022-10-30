package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ApplicationServer {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(5099);

        PasswordService passwordService = new PasswordService();
        Printer printer = new Printer();
        registry.rebind("printerService", new PrinterService(passwordService, printer));
    }
}
