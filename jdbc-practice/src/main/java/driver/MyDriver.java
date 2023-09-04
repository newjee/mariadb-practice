package driver;

import java.io.Console;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class MyDriver implements Driver {

    static {
        System.out.println("static code area");
        try {
            DriverManager.registerDriver(new MyDriver());
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        System.out.println("url:"+ url);
        System.out.println("info: " + info);

        return new MyConnection();
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return false;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
