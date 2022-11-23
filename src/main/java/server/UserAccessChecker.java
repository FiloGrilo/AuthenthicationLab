package server;

import server.loaders.UserOperationsLoader;

import java.util.Set;

public class UserAccessChecker {

    private UserAccessChecker() {
    }

    public static void check(String username, Operation operation) throws UserAccessException {
        Set<Operation> userOperations = UserOperationsLoader.userOperations.get(username);
        if(userOperations == null || userOperations.isEmpty()) {
            throw new UserAccessException("User with name '" + username + "' does not have assigned operations. Not permitted to invoke operation '" + operation.name() + "'");
        }
        if (!userOperations.contains(operation)) {
            throw new UserAccessException("User with name '" + username + "' not permitted to invoke operation '" + operation.name() + "'");
        }
    }
}
