package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

/**
 *  This class provides the printer server endpoints that client can use.
 */
public class PrinterFacade extends UnicastRemoteObject implements IPrinterFacade {

    private final IPasswordService passwordService;
    private final IPrinterService printerService;
    private final IUserService userService;
    private final IUserAccessService verifyAccess;
    public PrinterFacade(IPasswordService passwordService, IPrinterService printer, IUserService userService, IUserAccessService verifyAccess) throws RemoteException {
        super();
        this.passwordService = passwordService;
        this.printerService = printer;
        this.userService = userService;
        this.verifyAccess = verifyAccess;
    }

    @Override
    public void createUser(String username, char[] password) throws RemoteException {
        try {
            passwordService.saveUser(username, password);
            System.out.println("New user with username: " + username + " is created");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public boolean verifyUser(String username, char[] password) throws AuthenticationFailedException, RemoteException {
        try {
            if (passwordService.verifyUser(username, password)) {
                userService.addAuthenticatedUser(username);
                System.out.println("User with username " + username + " is successfully verified and can use the printer");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationFailedException("Authentication of user with username: " + username + " failed. User cannot use the printer");
        }
        System.out.println("User with username " + username + " is successfully verified and can use the printer");
        throw new AuthenticationFailedException("Authentication of user with username: " + username + " failed. User cannot use the printer");
    }

    @Override
    public String print(String username, String filename, String printer) throws Exception {
        userService.verifyUser(username);
        verifyAccess.checkUserAccess(username, Operation.print.getValue());

        return printerService.print(filename, printer);
    }

    @Override
    public String queue(String username, String printer) throws Exception {
        userService.verifyUser(username);
        verifyAccess.checkUserAccess(username, Operation.queue.getValue());
        return printerService.queue(printer);
    }

    @Override
    public void topQueue(String username, String printer, Integer job) throws Exception {
        userService.verifyUser(username);
        verifyAccess.checkUserAccess(username, Operation.topQueue.getValue());
        printerService.topQueue(printer, job);
    }

    @Override
    public void start(String username) throws Exception {
        userService.verifyUser(username);
        verifyAccess.checkUserAccess(username, Operation.start.getValue());
        printerService.start();
    }

    @Override
    public void stop(String username) throws Exception {
        userService.verifyUser(username);
        verifyAccess.checkUserAccess(username, Operation.stop.getValue());
        printerService.stop();

    }

    @Override
    public void restart(String username) throws Exception {
        userService.verifyUser(username);
        verifyAccess.checkUserAccess(username, Operation.restart.getValue());
        printerService.restart();
    }

    @Override
    public String status(String username) throws Exception {
        userService.verifyUser(username);
        verifyAccess.checkUserAccess(username, Operation.status.ordinal());
        return printerService.status();
    }

    @Override
    public String readConfig(String username, String parameter) throws Exception {
        userService.verifyUser(username);
        verifyAccess.checkUserAccess(username, Operation.readConfig.getValue());
        return printerService.readConfig(parameter);
    }

    @Override
    public void setConfig(String username, String parameter, String value) throws Exception {
        try {
            userService.verifyUser(username);
            verifyAccess.checkUserAccess(username, Operation.setConfig.getValue());
            printerService.setConfig(parameter, value);
        } catch (AuthenticationFailedException e) {
            System.out.println(e.getMessage());
        }
    }
}
