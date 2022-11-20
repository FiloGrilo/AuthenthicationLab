package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  This class loads operations for users when the server starts
 *
 */
public class UserOperationsLoader {
    private static final String SQL_QUERY_SELECT = "SELECT username, operation_id FROM user_operations_mapping";

    public static final Map<String, Set<Operation>> userOperations = new HashMap<>();

    private UserOperationsLoader() {
    }

    public static void load() throws Exception {
        try (Connection con = DataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(SQL_QUERY_SELECT)) {
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                String username = rst.getString(1);
                Operation operation = Operation.getFromValue(rst.getInt(2));
                put(username, operation);
            }
        }
    }

    private static void put(String username, Operation op) {
        if (userOperations.containsKey(username)) {
            userOperations.get(username).add(op);
        } else {
            Set<Operation> operations = new HashSet<>();
            operations.add(op);
            userOperations.put(username, operations);
        }
    }


}


