package server;


public interface IUserAccessService {
    void checkAccess(String username, int operationID) throws Exception;
}
