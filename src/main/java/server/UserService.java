package server;

import java.util.HashMap;
import java.util.Map;

public class UserService implements IUserService {
    //stores username and sessionStartTimeMillis
    private final Map<String, Long> authenticatedUsers = new HashMap<>();
    //5 min
    private static final int SESSION_TIMEOUT = 5 * 60 * 1000;

    @Override
    public void verifyUser(String username) throws AuthenticationFailedException {
        if (!authenticatedUsers.containsKey(username)) throw new AuthenticationFailedException("User is not authenticated yet!");
        long sessionStartTime =  authenticatedUsers.get(username);
        if (System.currentTimeMillis() - sessionStartTime >= SESSION_TIMEOUT) throw new AuthenticationFailedException("User session has expired! Try to log in again.");
    }

    @Override
    public void addAuthenticatedUser(String username) {
        authenticatedUsers.put(username, System.currentTimeMillis());
    }


}
