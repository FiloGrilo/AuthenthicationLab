package server.authentication;

import java.sql.SQLException;

public interface IPasswordService {
    boolean verifyUser(String username, char[] password) throws Exception;
    void saveUser(String username, char[] password) throws SQLException;

}
