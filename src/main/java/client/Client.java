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
        // create new user
        service.createUser(username, "test".toCharArray());
        // try to verify user with incorrect password
        try {
            System.out.println("\nTrying to verify user with incorrect password..");
            service.verifyUser(username, "incorrect".toCharArray());
        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
        // try to verify user with correct password
        System.out.println("\nTrying to verify user with correct password..");
        boolean isUserVerified = service.verifyUser(username, "test".toCharArray());
        if (isUserVerified) System.out.println("User with username '" + username + "'successfully verified!\n");

        // start the printing server
        System.out.println("\nStarting the server...\n");
        service.start(username);
        // get status of the printing server
        System.out.println("--------------------STATUS----------------------");
        String status = service.status(username);
        System.out.println(status + "\n");

        // set printing server config
        System.out.println("setting config...\n");
        service.setConfig(username, "param1", "value1");
        // read the setting config
        System.out.println("-------------------READ_CONFIG---------------------");
        String config = service.readConfig(username, "param1");
        System.out.println(config+ "\n");

        // print files on Printer 1
        System.out.println("--------------------PRINT----------------------");
        String file1 = service.print(username, "file1", "Printer1");
        System.out.println(file1);
        String file2 = service.print(username, "file2", "Printer1");
        System.out.println(file2);
        String file3 = service.print(username, "file3", "Printer1");
        System.out.println(file3);
        System.out.println("\n");

        // get the Printer1 queue
        System.out.println("---------------------QUEUE----------------------");
        String queue = service.queue(username, "Printer1");
        System.out.println(queue);

        //move job to the top of the queue
        System.out.println("moving job 2 to the top...\n");
        service.topQueue(username, "Printer1", 2);
        String newQueue = service.queue(username, "Printer1");
        System.out.println("------------QUEUE_AFTER_MOVING_TO_TOP-----------");
        System.out.println(newQueue);

        // restart the printing server
        System.out.println("restarting the server...\n");
        service.restart(username);

        // get the Printer1 queue after restarting
        System.out.println("---------------QUEUE_AFTER_RESTART------------------");
        String queueAfterRestarting = service.queue(username, "Printer1");
        System.out.println(queueAfterRestarting);
    }

    private static String generateRandomUsername() {
       return "user_"  + new Random().nextInt();
    }
}
