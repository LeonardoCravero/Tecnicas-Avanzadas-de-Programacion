package Modelo;

import Modelo.Productos;
import Config.Conexion;
import java.sql.*;
import java.util.*;

public class ProductosDao {
    
    // Variable de Conexion
    Connection conexion;
    
    // Constructor de clase
    public ProductosDao() {
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }

    // Metodo para listar productos
    public List<Productos> listarProductos() {
        PreparedStatement ps;
        ResultSet rs;
        List<Productos> Lista = new ArrayList<>();
        try {
            ps = conexion.prepareStatement("SELECT id, nombre, descripcion, precio_compra, precio_venta, cantidad FROM producto");
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio_compra = rs.getDouble("precio_compra");
                double precio_venta = rs.getDouble("precio_venta");
                int cantidad = rs.getInt("cantidad");

                Productos producto = new Productos(id, nombre, descripcion, precio_compra, precio_venta, cantidad);

                Lista.add(producto);
            }
            return Lista;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    // Metodo para mostrar productos
    public Productos mostrarProductos(int _id){
        PreparedStatement ps;
        ResultSet rs;
        Productos producto = null;
        
        try {
            ps = conexion.prepareStatement("SELECT * FROM producto WHERE id=?");
            ps.setInt(1,_id);
            rs = ps.executeQuery();
            
            while (rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                double precio_compra = rs.getDouble("precio_compra");
                double precio_venta = rs.getDouble("precio_venta");
                int cantidad = rs.getInt("cantidad");
                
                producto = new Productos(id, nombre, descripcion, precio_compra, precio_venta, cantidad);
            }
            return producto;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    // Metodo para insertar productos
    public boolean insertar(Productos producto){
        PreparedStatement ps;
        
        try {
            ps = conexion.prepareStatement("INSERT INTO producto(nombre, descripcion, precio_compra, precio_venta, cantidad) VALUES(?,?,?,?,?)");
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio_compra());
            ps.setDouble(4, producto.getPrecio_venta());
            ps.setInt(5, producto.getCantidad());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    // Metodo para actualizar productos
    public boolean actualizar(Productos producto){
        PreparedStatement ps;
        
        try {
            ps = conexion.prepareStatement("UPDATE producto SET nombre=?, descripcion=?, precio_compra=?, precio_venta=?, cantidad=? WHERE id=?");
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio_compra());
            ps.setDouble(4, producto.getPrecio_venta());
            ps.setInt(5, producto.getCantidad());
            ps.setInt(6, producto.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    // Metodo para eliminar productos
    public boolean eliminar(int _id){
        PreparedStatement ps;
        
        try {
            ps = conexion.prepareStatement("DELETE FROM producto WHERE id=?");
            ps.setInt(1, _id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
