package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserAccessService implements IUserAccessService {
    private static final String SQL_QUERY_SELECT = "SELECT username, operation_id FROM user_operations_mapping WHERE username = ? AND operation_id = ?";

    @Override
    public void checkUserAccess(String username, int operationID) throws Exception {
        try (Connection con = DataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(SQL_QUERY_SELECT)) {
            pst.setString(1, username);
            pst.setInt(2, operationID);
            ResultSet rst = pst.executeQuery();
            if (!rst.next()) throw new UserAccessException("User with name '" + username + "' not allowed to invoke operation '" + Operation.getFromValue(operationID) + "'");
        }
    }

}


