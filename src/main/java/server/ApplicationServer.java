package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class ApplicationServer {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(5099);

        PasswordService passwordService = new PasswordService();
        Map<String, Printer> printers = generatePrinters();

        //loading user roles
        UserRolesLoader.load();
        //loading user operations
        UserOperationsLoader.load();
        //loading role operations
        RoleOperationsLoader.load();


        PrinterService printerService = new PrinterService(printers);
        UserService userService = new UserService();

        //setting access policy
        AccessPolicy accessPolicy = AccessPolicy.roleBased;

        registry.rebind("printerService", new PrinterFacade(passwordService, printerService, userService, accessPolicy));
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
