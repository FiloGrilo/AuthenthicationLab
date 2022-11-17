package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserOperation implements IUserOperation{
    private static final String SQL_QUERY_SELECT = "SELECT username AND procid FROM table WHERE username = ? AND procid = ?";

    @Override
    public boolean controlAccess(String username, int operationID) throws Exception {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_QUERY_SELECT)) {
            pst.setString(1, username);
            pst.setInt(2, operationID);
            ResultSet rst = pst.executeQuery();
            if (rst.next() == false){
                return false;
            } else{
                return true;
            }
        }
        //throw new Exception("Could not verify if user can access the function");
    }

}


