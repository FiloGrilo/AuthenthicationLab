package server;

import server.loaders.RoleOperationsLoader;
import server.loaders.UserRolesLoader;

import java.util.Set;

public class RoleAccessChecker {

    private RoleAccessChecker() {
    }

    public static void check(String username, Operation operation) throws RoleAccessException {
        Set<Role> userRoles = UserRolesLoader.getUserRoles(username);
        //could not find assigned roles for this user
        if (userRoles == null || userRoles.isEmpty()) {
            throw new RoleAccessException("User with name '" + username + "' does not have any assigned roles and not permitted to invoke operation '" + operation.name() + "'");
        }
        //check if user's role(s) contain operation that user wants to invoke
        if (userRoles.stream().noneMatch(role -> RoleOperationsLoader.doesRoleContainOperation(role, operation))) {
            throw new RoleAccessException("User with name '" + username + "' and roles " + userRoles + " not permitted to invoke operation '" + operation.name() + "'");
        }
    }
}
