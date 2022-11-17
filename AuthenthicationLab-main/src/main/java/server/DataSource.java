package server;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl( "jdbc:postgresql://mouse.db.elephantsql.com/lquunnaf" );
        config.setUsername("lquunnaf");
        config.setPassword("JoIwL78qOtnDehULsce_GHUbBR-cQD7Z");
        config.setDriverClassName("org.postgresql.Driver");
        config.setPoolName("CarpHikariCP");
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048");
        config.setConnectionTestQuery("select 1");
        ds = new HikariDataSource( config );
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


}
