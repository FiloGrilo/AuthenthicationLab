package src.main.java.server;

interface IPasswordService {
    boolean verifyUser(String username, String password);
    void saveUser(String username, String password);
}
