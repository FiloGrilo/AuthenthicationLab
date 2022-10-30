package server;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariProxyConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoadDriver {
    public static void main(String[] args) {
        try {
            HikariDataSource dataSource = DataSource.getDataSource();
            if(dataSource.isRunning()) System.out.println("dataSource is running...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
