package server;

public interface IUserService {
    void verifyUser(String username) throws AuthenticationFailedException;
    void addAuthenticatedUser(String username);
}
