package repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Util util;
    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DATABASE_URL = "jdbc:mysql://localhost:3306/crud-jdbc";
    private String USER = "root";
    private String PASSWORD = "root";

    public static Util getUtil(){
        if(util == null){
            util = new Util();
        }
        return util;
    }

    private Util(){}

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
