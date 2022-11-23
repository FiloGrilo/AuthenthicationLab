package server.loaders;

import server.DataSource;
import server.Operation;
import server.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 *  This class loads operations for roles when the server starts
 *
 */
public class RoleOperationsLoader {

    private RoleOperationsLoader() {
    }

    private static final String SQL_QUERY_SELECT =
            "SELECT r.role, o.operation FROM roles_operations_mapping ro " +
            "join roles r on r.id = ro.role_id " +
            "join operations o on o.id = ro.operation_id";

    public static final Map<Role, Set<Operation>> roleOperations = new HashMap<>();


    public static void load() throws Exception {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY_SELECT)) {
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                Role role = Role.valueOf(rst.getString(1));
                Operation operation = Operation.valueOf(rst.getString(2));
                put(role, operation);
            }
        }
    }

    private static void put(Role role, Operation op) {
        if (roleOperations.containsKey(role)) {
            roleOperations.get(role).add(op);
        } else {
            Set<Operation> operations = new HashSet<>();
            operations.add(op);
            roleOperations.put(role, operations);
        }
    }


    public static boolean doesRoleContainOperation(Role role, Operation op) {
       return roleOperations.get(role).contains(op);
    }
}
