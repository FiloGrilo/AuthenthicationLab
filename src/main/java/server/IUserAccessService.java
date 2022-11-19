package server;


public interface IUserAccessService {
    void checkUserAccess(String username, int operationID) throws Exception;
}
