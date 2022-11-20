package server;

import java.util.Set;

public class RoleAccessChecker {

    private RoleAccessChecker() {
    }

    public static void check(String username, Operation operation) throws RoleAccessException {
        Set<Role> roles = UserRolesLoader.getUserRoles(username);
        if (roles.stream().noneMatch(role -> RoleOperationsLoader.doesRoleContainOperation(role, operation))) {
            throw new RoleAccessException("User with name '" + username + "' and roles " + roles + " not permitted to invoke operation '" + operation.name() + "'");
        }
    }
}
