package src.main.java.server;

//TODO needs to be implemented
public class PasswordService implements IPasswordService {

    @Override
    public boolean verifyUser(String username, String password) {
        //go to database
        //search for username
        //use same hash function on password passed and check if its equal to saved password associated with that username
        //if yes, return true
        //if not, return false
        return false;
    }

    @Override
    public void saveUser(String username, String password) {
        //go to database
        //check if there is no same username
        //if it is, return system.out.println("Username already exists, please choose another one.");
        //if not, create username and store in database the username, the salt used and the hash function of password+salt
    }
}
