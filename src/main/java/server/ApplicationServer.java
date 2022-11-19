package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class ApplicationServer {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(5099);

        PasswordService passwordService = new PasswordService();
        Map<String, Printer> printers = generatePrinters();
        PrinterService printerService = new PrinterService(printers);
        UserService userService = new UserService();
        UserAccessService userOperation = new UserAccessService();
        registry.rebind("printerService", new PrinterFacade(passwordService, printerService, userService, userOperation));
    }

    private static Map<String, Printer> generatePrinters() {
        Printer printer1 = new Printer("Printer1");
        Printer printer2 = new Printer("Printer2");
        Printer printer3 = new Printer("Printer3");
        Map<String, Printer> printers = new HashMap<>();
        printers.put("Printer1", printer1);
        printers.put("Printer2", printer2);
        printers.put("Printer3", printer3);
        return printers;
    }
 }
