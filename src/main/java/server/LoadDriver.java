package src.main.java.server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoadDriver {
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex){
            System.out.println("Driver not registered");
        }

        Connection conn;

        try{
            String url = "jdbc:mysql://cp26.webserver.pt:3306/test?";
            String user = "root";
            String pass = "secret";

            conn = DriverManager.getConnection(url, user, pass);

            if(conn != null){
                System.out.println("Connected to database");
            }
        } catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
