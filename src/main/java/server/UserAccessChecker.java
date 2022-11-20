package server;

public class UserAccessChecker {

    private UserAccessChecker() {
    }

    public static void check(String username, Operation operation) throws UserAccessException {
        if (!UserOperationsLoader.userOperations.get(username).contains(operation)) {
            throw new UserAccessException("User with name '" + username + "' not permitted to invoke operation '" + operation.name() + "'");
        }
    }
}
