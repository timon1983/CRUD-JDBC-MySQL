package Repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DATABASE_URL = "jdbc:mysql://localhost:3306/crud-jdbc";
    String USER = "root";
    String PASSWORD = "root";

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
}
