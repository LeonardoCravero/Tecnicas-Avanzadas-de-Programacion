package Controlador;

import Modelo.*;
import java.util.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    // Metodo doGet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Variables iniciales
        ProductosDao productosDao = new ProductosDao();
        RequestDispatcher dispatcher;
        String accion = request.getParameter("accion");
        
        if (accion == null || accion.isEmpty()){
            accion = "listar";
        }
        
        // Tipos de acciones
        switch (accion) {
            
            // Accion de listar productos
            case("listar"):
                List<Productos> listaProductos = productosDao.listarProductos();
                request.setAttribute("lista", listaProductos);
                dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                return;
            
            // Accion de nuevo producto
            case ("nuevo"):
                listaProductos = productosDao.listarProductos();
                request.setAttribute("lista", listaProductos);
                request.setAttribute("modal", "nuevo");
                dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            
            // Accion de insertar producto
            case ("insertar"):
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                double precio_compra = Double.parseDouble(request.getParameter("precio_compra"));
                double precio_venta = Double.parseDouble(request.getParameter("precio_venta"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                Productos producto = new Productos(0, nombre, descripcion, precio_compra, precio_venta, cantidad);
                productosDao.insertar(producto);
                response.sendRedirect("Controlador?accion=listar");
                return;
            
            // Accion de modificar producto
            case ("modificar"):
                int id = Integer.parseInt(request.getParameter("id"));
                producto = productosDao.mostrarProductos(id);
                request.setAttribute("producto", producto);
                request.setAttribute("modal", "modificar");
                listaProductos = productosDao.listarProductos();
                request.setAttribute("lista", listaProductos);
                dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                return;
            
            // Accion de actualizar producto
            case ("actualizar"):
                id = Integer.parseInt(request.getParameter("id"));
                nombre = request.getParameter("nombre");
                descripcion = request.getParameter("descripcion");
                precio_compra = Double.parseDouble(request.getParameter("precio_compra"));
                precio_venta = Double.parseDouble(request.getParameter("precio_venta"));
                cantidad = Integer.parseInt(request.getParameter("cantidad"));
                producto = new Productos(id, nombre, descripcion, precio_compra, precio_venta, cantidad);
                productosDao.actualizar(producto);
                response.sendRedirect("Controlador?accion=listar");
                return;
            
            // Accion de eliminar producto
            case ("eliminar"):
                id = Integer.parseInt(request.getParameter("id"));
                productosDao.eliminar(id);
                response.sendRedirect("Controlador?accion=listar");
                return;
            
            // Accion default
            default:
                listaProductos = productosDao.listarProductos();
                request.setAttribute("lista", listaProductos);
                dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
        }
        
    }

    // Metodo doPost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // Metodo getServletInfo
    @Override
    public String getServletInfo() {
        return "Short descripcion";
    }
}
