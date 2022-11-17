package server;

import java.sql.SQLException;

interface IPasswordService {
    boolean verifyUser(String username, char[] password) throws Exception;
    void saveUser(String username, char[] password) throws SQLException;

}
