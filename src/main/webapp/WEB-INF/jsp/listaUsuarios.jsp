<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Usuarios</title>
    <style>
        body { font-family: Arial; background: #f8f9fa; margin: 0; padding: 20px; }
        .container { max-width: 800px; margin: auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .nav { margin-bottom: 20px; padding-bottom: 10px; border-bottom: 2px solid #eee; }
        .nav a { text-decoration: none; padding: 8px 15px; background: #007bff; color: white; border-radius: 4px; margin-right: 10px; }
        .nav a.gris { background: #6c757d; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border-bottom: 1px solid #ddd; text-align: left; }
        .btn { padding: 6px 12px; text-decoration: none; border-radius: 4px; color: white; font-size: 14px; display: inline-block; }
        .btn-nuevo { background: #28a745; margin-bottom: 15px; display: inline-block; }
        .btn-editar { background: #ffc107; color: black; }
        .btn-borrar { background: #dc3545; }
        .msg { color: red; text-align: center; margin-bottom: 10px; background: #f8d7da; padding: 10px; border-radius: 4px; }
    </style>
</head>
<body>
<div class="container">
    <div class="nav">
        <a href="usuarios">Usuarios</a>
        <a href="movimientos" class="gris">Movimientos</a>
    </div>

    <h2>Lista de Usuarios</h2>

    <% if (request.getAttribute("error") != null) { %>
    <div class="msg"><%= request.getAttribute("error") %></div>
    <% } %>

    <a href="usuarios?accion=nuevo" class="btn btn-nuevo">+ Nuevo Usuario</a>

    <table>
        <tr>
            <th>ID</th>
            <th>Usuario</th>
            <th>Nombre</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="u" items="${lista}">
            <tr>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.nombre}</td>
                <td>
                    <a href="usuarios?accion=editar&id=${u.id}" class="btn btn-editar">Editar</a>
                    <a href="usuarios?accion=eliminar&id=${u.id}" class="btn btn-borrar" onclick="return confirm('¿Borrar usuario?')">Borrar</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>