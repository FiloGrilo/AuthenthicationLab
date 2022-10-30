package server;

import java.sql.SQLException;

interface IPasswordService {
    boolean verifyUser(String username, String password) throws Exception;
    void saveUser(String username, String password) throws SQLException;
}
