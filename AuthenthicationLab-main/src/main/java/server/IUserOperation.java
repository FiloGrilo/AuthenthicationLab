package server;

import java.sql.SQLException;
public interface IUserOperation {
    boolean controlAccess(String username, int operationID) throws Exception;
}
