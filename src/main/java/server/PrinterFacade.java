package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PrinterFacade extends UnicastRemoteObject implements IPrinterFacade {

    private final IPasswordService passwordService;
    private final IPrinterService printerService;
    private Set<String> authenticatedUsers = new HashSet<>();

    public PrinterFacade(IPasswordService passwordService, IPrinterService printer) throws RemoteException {
        super();
        this.passwordService = passwordService;
        this.printerService = printer;
    }

    @Override
    public void createUser(String username, String password) throws RemoteException {
        try {
            passwordService.saveUser(username, password);
            System.out.println("New user with username: " + username + " is created");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public boolean verifyUser(String username, String password) throws AuthenticationFailedException, RemoteException {
        try {
            if (passwordService.verifyUser(username, password)) {
                authenticatedUsers.add(username);
                System.out.println("User with username " + username + " is successfully verified and can use the printer");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationFailedException("Authentication of user with username: " + username + " is failed. User cannot use the printer");
        }
        throw new AuthenticationFailedException("Authentication of user with username: " + username + " is failed. User cannot use the printer");
    }


    @Override
    public void print(String username, String filename, String printer) {
        try {
            checkUserIsAuthenticated(username);
            printerService.print(filename, printer);
        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void queue(String username, String printer) {
        try {
            checkUserIsAuthenticated(username);
            printerService.queue(printer);
        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void topQueue(String username, String printer, Integer job) {
        try {
            checkUserIsAuthenticated(username);
            printerService.topQueue(printer, job);
        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(String username) {
        try {
            checkUserIsAuthenticated(username);
            printerService.start();
        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void stop(String username) {
        try {
            checkUserIsAuthenticated(username);
            printerService.stop();
        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void restart(String username) {
        try {
            checkUserIsAuthenticated(username);
            authenticatedUsers.clear();
            printerService.restart();
        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void status(String username) {
        try {
            checkUserIsAuthenticated(username);
            printerService.status();
        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void readConfig(String username, String parameter) {
        try {
            checkUserIsAuthenticated(username);
            printerService.readConfig(parameter);
        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setConfig(String username, String parameter, String value) {
        try {
            checkUserIsAuthenticated(username);
            printerService.setConfig(parameter, value);
        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkUserIsAuthenticated(String username) throws AuthenticationFailedException {
       if (!authenticatedUsers.contains(username)) throw new AuthenticationFailedException("User is not authenticated yet!");
    }

}
