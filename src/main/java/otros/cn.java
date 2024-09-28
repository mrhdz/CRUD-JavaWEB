package otros;

import java.sql.DriverManager;

public class cn {
    java.sql.Connection conexion;
    public cn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion=(java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionvehiculos","root","");            
        } catch (Exception e) {
            System.err.println("Error"+e);
        }
    }
    public java.sql.Connection getConexion() {
        return conexion;
    }
}