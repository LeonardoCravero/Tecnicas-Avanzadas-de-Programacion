package Config;

import java.sql.*;

public class Conexion {
    
    // Variables estaticas para generar conexion
    private static final String url = "jdbc:mysql://localhost:3306/tienda?useSSL=false&serverTimezone=UTC";
    private static final String usuario = "root";
    private static final String contraseña = "5830";
    
    // Metodo getConexion
    public Connection getConexion(){
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexion exitosa");
            return conexion;
        } catch(SQLException e){
            System.out.println(e.toString());
            return null;
        } catch(ClassNotFoundException e){
            return null;
        }
        
    }
}
