package src.main.java.server;

public interface IPrinterService {
    void createUser(String username, String password);
    IPrinter verifyUser(String username, String password) throws AuthenticationFailedException;
}
