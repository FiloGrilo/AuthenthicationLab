package server;

public interface IRoleAccessService {
    void checkAccess(String username, int operationID) throws Exception;
}
