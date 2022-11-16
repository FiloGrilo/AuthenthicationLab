package server;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class PasswordService implements IPasswordService {

    private static final String SQL_QUERY_INSERT = "insert into users(username, password, salt) values (?, ?, ?)";
    private static final String SQL_QUERY_SELECT = "select username, password, salt from users where username = ?";

    private static final Random RANDOM = new SecureRandom();
    private static final int KEY_LENGTH = 256;


    /**
     *  This method verifies if user in the database and has a correct password
     *
     * @param password the password to be verified
     * @param username username
     */
    @Override
    public boolean verifyUser(String username, char[] password) throws Exception {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY_SELECT )) {
            pst.setString(1, username);
            ResultSet rst = pst.executeQuery();
            String hashedPassword = null;
            String salt = null;
            while(rst.next()) {
                hashedPassword = rst.getString(2);
                salt = rst.getString(3);
            }
            if(hashedPassword != null && salt != null) {
                return isExpectedPassword(password, salt, hashedPassword.toCharArray());
            } else {
                System.out.println("User with username: " + username + " not found!");
            }
        }
        throw new Exception("Could not check the password");
    }

    /**
     *  This method saves the User into database with hashed password and salt
     *
     * @param password the password to be hashed
     * @param username username to be saved
     */
    @Override
    public void saveUser(final String username, char[] password) throws SQLException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement( SQL_QUERY_INSERT )) {
             pst.setString(1, username);
             byte[] saltBytes = new byte[16];
             RANDOM.nextBytes(saltBytes);
             String salt = Base64.getEncoder().encodeToString(saltBytes);
             pst.setString(3, salt);
             pst.setString(2, hash(password, salt));
             int row = pst.executeUpdate();
        }
    }

    /**
     * Returns a salted and hashed password using the provided hash.<br>
     *
     * @param password the password to be hashed
     * @param salt     a 16 bytes salt
     *
     * @return the hashed password with a salt
     */
    private String hash(char[] password, String salt) {
        PBEKeySpec spec = new PBEKeySpec(password, Base64.getDecoder().decode(salt), 10000, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return Base64.getEncoder().encodeToString(skf.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    /**
     * Returns true if the given password and salt match the hashed value, false otherwise.<br>
     *
     * @param password     the password to check
     * @param salt         the salt used to hash the password
     * @param expectedHash the expected hashed value of the password
     *
     * @return true if the given password and salt match the hashed value, false otherwise
     */
    private boolean isExpectedPassword(char[] password, String salt, char[] expectedHash) {
        char[] pwdHash = hash(password, salt).toCharArray();
        Arrays.fill(password, Character.MIN_VALUE);
        if (pwdHash.length != expectedHash.length) return false;
        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != expectedHash[i]) return false;
        }
        return true;
    }

    private boolean checkAccess(String username, int op_id) {

    }

}
