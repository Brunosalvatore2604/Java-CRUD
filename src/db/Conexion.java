package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    private static final String URL = "jdbc:mysql://root:fRPzVvNWUkLFFLHClOzYKPmyQxOPoZTG@tramway.proxy.rlwy.net:47450/railway";
    private static final String USER = "root";
    private static final String PASS = "KgGWesCIKySnrPUBqRsDnqknulAKlrGi";

    public static Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión exitosa a la base de datos.");  
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver JDBC no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base");
            e.printStackTrace();
        }
        return conn;
    }
}
