<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Stock</title>
        <!-- Llamando a carpeta de estilos para html -->
        <link rel="stylesheet" href="./CSS/Estilos.css"/>
    </head>
    <body>
        <!-- Redireccionamiento para Listar Productos -->
        <c:if test="${empty lista}">
            <c:redirect url="Controlador?accion=listar"/>
        </c:if>
        
        <h1>Bienvenido al Sistema de Gestion de Stock</h1>
        
        <!-- checkboxes que controlan modales; el servlet setea request.setAttribute("modal","nuevo"/"modificar") -->
        <input type="checkbox" id="modalNuevo" class="modal-toggle" <%= "nuevo".equals(request.getAttribute("modal")) ? "checked" : ""%> />
        <input type="checkbox" id="modalModificar" class="modal-toggle" <%= "modificar".equals(request.getAttribute("modal")) ? "checked" : ""%> />
        
        <!-- Redireccionamiento para registrar nuevo Producto -->
        <a href="Controlador?accion=nuevo">Nuevo Registro</a><br/><br/>
        
        <!-- Muestra de Productos por pantalla -->
        <table border="1" width="70%">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th>Precio de Compra</th>
                    <th>Precio de Venta</th>
                    <th>Cantidad</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="producto" items="${lista}">
                    <tr>
                        <td><c:out value="${producto.nombre}"/></td>
                        <td><c:out value="${producto.descripcion}"/></td>
                        <td><c:out value="${producto.precio_compra}"/></td>
                        <td><c:out value="${producto.precio_venta}"/></td>
                        <td><c:out value="${producto.cantidad}"/></td>
                        <td>
                            <a href="Controlador?accion=modificar&id=<c:out value="${producto.id}"/>">Modificar</a>
                        </td>
                        <td>
                            <a href="Controlador?accion=eliminar&id=<c:out value="${producto.id}"/>" onclick="return confirm('Eliminar?')">Eliminar</a>
                        </td>
                    </tr> 
                </c:forEach>
            </tbody>
        </table>

        <!-- Pantalla emergente para nuevo producto -->
        <div class="modal modal-nuevo">
            <div class="modal-box">
                <h2>Nuevo Producto</h2>
                <form action="Controlador?accion=insertar" method="post">
                    
                    <label>Nombre</label><br/>
                    <input type="text" name="nombre" required><br/><br/>

                    <label>Descripcion</label><br/>
                    <textarea name="descripcion" rows="3"></textarea><br/><br/>

                    <label>Precio de Compra</label><br/>
                    <input type="number" name="precio_compra" step="0.01" required><br/><br/>

                    <label>Precio de Venta</label><br/>
                    <input type="number" name="precio_venta" step="0.01" required><br/><br/>

                    <label>Cantidad</label><br/>
                    <input type="number" name="cantidad" required><br/><br/>

                    <div class="actions">
                        <button type="submit">Guardar</button>
                        <label for="modalNuevo" class="close-label">Cerrar</label>
                    </div>
                </form>
            </div>
        </div>
        
        <!-- Pantalla emergente para modificar producto -->
        <div class="modal modal-modificar">
            <div class="modal-box">
                <h2>Modificar Producto</h2>
                <c:if test="${not empty producto}">
                    <form action="Controlador?accion=insertar" method="post">
                        <input type="hidden" name="id" value="${producto.id}">
                        
                        <label>Nombre</label><br/>
                        <input type="text" name="nombre" value="${producto.nombre}" required><br/><br/>

                        <label>Descripcion</label><br/>
                        <textarea name="descripcion" rows="3">${producto.descripcion}</textarea><br/><br/>

                        <label>Precio de Compra</label><br/>
                        <input type="number" name="precio_compra" step="0.01" value="${producto.precio_compra}" required><br/><br/>

                        <label>Precio de Venta</label><br/>
                        <input type="number" name="precio_venta" step="0.01" value="${producto.precio_venta}" required><br/><br/>

                        <label>Cantidad</label><br/>
                        <input type="number" name="cantidad" value="${producto.cantidad}" required><br/><br/>

                        <div class="actions">
                            <button type="submit">Actualizar</button>
                            <label for="modalModificar" class="close-label">Cerrar</label>
                        </div>
                    </form>
                </c:if>
                <c:if test="${empty producto}">
                    <p>Producto no encontrado</p>
                    <label for="modalModificar" class="close-label">Cerrar</label>
                </c:if>
            </div>
        </div>
    </body>
</html>
