package client;

import server.IPrinterFacade;
import server.UserAccessException;
import java.rmi.Naming;
import java.util.Random;

public class Client {
    public static void main(String[] args) throws Exception {
        IPrinterFacade service = (IPrinterFacade) Naming.lookup("rmi://localhost:5099/printerService");
        evaluateUserBasedAccessControl(service);
    }

    private static void evaluateUserBasedAccessControl(IPrinterFacade service) throws Exception {
        System.out.println("\n---------------Results of User Based Access Control----------------- \n");
        checkForAlice(service);
        checkForFred(service);
        checkForCecilia(service);
        checkForBob(service);
        checkForDavid(service);
        checkForGeorge(service);
    }

    private static void checkForBob(IPrinterFacade service) throws Exception {
        printLine();
        System.out.println("\tChecking access for BOB:\n");
        String bob = "Bob";
        // log in
        service.verifyUser(bob, "test".toCharArray());
        checkForUser(service, bob);
    }

    private static void checkForDavid(IPrinterFacade service) throws Exception {
        printLine();
        System.out.println("\tChecking access for DAVID:\n");
        String david = "David";
        // log in
        service.verifyUser(david, "test".toCharArray());
        checkForUser(service, david);
    }

    private static void checkForGeorge(IPrinterFacade service) throws Exception {
        printLine();
        System.out.println("\tChecking access for GEORGE:\n");
        String george = "George";
        // log in
        service.verifyUser(george, "test".toCharArray());
        checkForUser(service, george);
    }

    private static void checkForCecilia(IPrinterFacade service) throws Exception {
        printLine();
        System.out.println("\tChecking access for CECILIA:\n");
        String cecilia = "Cecilia";
        // log in
        service.verifyUser(cecilia, "test".toCharArray());
        checkForUser(service, cecilia);

    }

    private static void checkForFred(IPrinterFacade service) throws Exception {
        printLine();
        System.out.println("\tChecking access for FRED:\n");
        String fred = "Fred";
        // log in
        service.verifyUser(fred, "test".toCharArray());
        checkForUser(service, fred);
    }

    private static void checkForAlice(IPrinterFacade service) throws Exception {
        printLine();
        System.out.println("\tChecking access for ALICE:\n");
        String alice = "Alice";
        // log in
        service.verifyUser(alice, "test".toCharArray());
        //catch UserAccessException if user is not allowed to perform an operation
        checkForUser(service, alice);
    }

    private static void checkForUser(IPrinterFacade service, String username) throws Exception {
        // User starts the printing serve
        System.out.println("Start:");
        try {
            service.start(username);
            System.out.println(username + " successfully started the printer\n");
        } catch (UserAccessException e) {
            System.out.println(e.getMessage() + "\n");
        }

        // User gets status of the printing server
        System.out.println("Status:");
        try {
            String status = service.status(username);
            System.out.println(username + " reads the status of printer: " + status);
        } catch (UserAccessException e) {
            System.out.println(e.getMessage() + "\n");
        }

        // User sets the printing server config
        System.out.println("SetConfig:");
        String param = generateRandomParam();
        try {
            service.setConfig(username, param, "value1");
            System.out.println(username + " sets the printer config for parameter '" + param + "'\n");
        } catch (UserAccessException e) {
            System.out.println(e.getMessage() + "\n");
        }

        // User reads the setting config
        System.out.println("ReadConfig:");
        try {
            String config = service.readConfig(username, param);
            System.out.println(username + " reads the printer config. " + config);
        } catch (UserAccessException e) {
            System.out.println(e.getMessage() + "\n");
        }

        // User prints files
        System.out.println("Print:");
        try {
            String printStatus1 = service.print(username, generateRandomFilename(), "Printer1");
            String printStatus2 = service.print(username, generateRandomFilename(), "Printer1");
            System.out.println(printStatus1);
            System.out.println(printStatus2);
        } catch (UserAccessException e) {
            System.out.println(e.getMessage() + "\n");
        }

        // User lists the print queue
        System.out.println("\nQueue:");
        try {
            String queueList = service.queue(username, "Printer1");
            System.out.println(queueList);
        } catch (UserAccessException e) {
            System.out.println(e.getMessage() + "\n");
        }


        // User moves job to the top of the queue
        System.out.println("TopQueue:");
        try {
            int job = 2;
            service.topQueue(username, "Printer1", job);
            System.out.println(username + " moves job: " + job + " to the top of the queue\n");
        } catch (UserAccessException e) {
            System.out.println(e.getMessage() + "\n");
        }

        // User stops the server
        System.out.println("Stop:");
        try {
            service.stop(username);
            System.out.println(username + " stopped the server\n");
        } catch (UserAccessException e) {
            System.out.println(e.getMessage() + "\n");
        }

        // User restarts the server
        System.out.println("Restart:");
        try {
            service.restart(username);
            System.out.println(username + " restarted the server\n");
        } catch (UserAccessException e) {
            System.out.println(e.getMessage() + "\n");
        }

    }

    private static String generateRandomParam() {
        return "param_"  + new Random().nextInt();
    }

    private static String generateRandomFilename() {
        return "file_"  + new Random().nextInt();
    }

    private static void printLine() {
        System.out.println("----------------------------------------------");
    }
}
