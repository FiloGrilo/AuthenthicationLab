package server.loaders;

import server.DataSource;
import server.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserRolesLoader {

    private UserRolesLoader() {
    }

    private static final String SQL_QUERY_SELECT =
            "SELECT u.username, r.role FROM users_roles_mapping ur " +
                    "join users u on u.username = ur.username " +
                    "join roles r on r.id = ur.role_id";

    public static final Map<String, Set<Role>> userRoles = new HashMap<>();


    public static void load() throws Exception {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY_SELECT)) {
             ResultSet rst = pst.executeQuery();
             while (rst.next()) {
                String username = rst.getString(1);
                Role role = Role.valueOf(rst.getString(2));
                put(username, role);
            }
        }
    }

    private static void put(String username, Role op) {
        if (userRoles.containsKey(username)) {
            userRoles.get(username).add(op);
        } else {
            Set<Role> roles = new HashSet<>();
            roles.add(op);
            userRoles.put(username, roles);
        }
    }


    public static Set<Role> getUserRoles(String username) {
        return userRoles.get(username);
    }
}
