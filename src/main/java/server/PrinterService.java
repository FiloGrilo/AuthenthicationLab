package src.main.java.server;

public class PrinterService implements IPrinterService {

    private final IPasswordService passwordService;
    private final IPrinter printer;

    public PrinterService(IPasswordService passwordService, IPrinter printer) {
        this.passwordService = passwordService;
        this.printer = printer;
    }

    @Override
    public void createUser(String username, String password) {
        passwordService.saveUser(username, password);
        System.out.println("New user with username: " + username + "is created");
    }

    @Override
    public IPrinter verifyUser(String username, String password) throws AuthenticationFailedException {
        if(passwordService.verifyUser(username, password)) {
            System.out.println("User with username " + username + "is successfully verified and can use the printer");
            return printer;
        }
        throw new AuthenticationFailedException("Authentication of user with username: " + username + "is failed. User cannot use the printer");
    }
}
