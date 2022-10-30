package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class PrinterService extends UnicastRemoteObject implements IPrinterService  {

    private final IPasswordService passwordService;
    private final IPrinter printer;

    public PrinterService(IPasswordService passwordService, IPrinter printer) throws RemoteException {
        super();
        this.passwordService = passwordService;
        this.printer = printer;
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
            if(passwordService.verifyUser(username, password)) {
                System.out.println("User with username " + username + " is successfully verified and can use the printer");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationFailedException("Authentication of user with username: " + username + " is failed. User cannot use the printer");
        }
        throw new AuthenticationFailedException("Authentication of user with username: " + username + " is failed. User cannot use the printer");
    }

}
