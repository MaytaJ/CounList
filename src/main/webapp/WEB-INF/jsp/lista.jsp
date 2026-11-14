<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Movimientos</title>
    <style>
        body { font-family: Arial; background: #f8f9fa; margin: 0; padding: 20px; }
        .container { max-width: 800px; margin: auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .nav { margin-bottom: 20px; padding-bottom: 10px; border-bottom: 2px solid #eee; }
        .nav a { text-decoration: none; padding: 8px 15px; background: #6c757d; color: white; border-radius: 4px; margin-right: 10px; }
        .nav a.activo { background: #007bff; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border-bottom: 1px solid #ddd; text-align: left; }
        .btn { padding: 6px 12px; text-decoration: none; border-radius: 4px; color: white; font-size: 14px; display: inline-block;}
        .btn-nuevo { background: #28a745; }
        .btn-editar { background: #ffc107; color: black; }
        .btn-borrar { background: #dc3545; }
        .buscador { display: flex; margin-top: 20px; gap: 10px; }
        .buscador input { padding: 8px; width: 300px; border: 1px solid #ccc; border-radius: 4px;}
    </style>
</head>
<body>
<div class="container">
    <div class="nav">
        <a href="usuarios">Usuarios</a>
        <a href="movimientos" class="activo">Movimientos</a>
    </div>

    <h2>Lista de Movimientos</h2>

    <div class="buscador">
        <form action="movimientos" method="GET" style="display: flex; gap: 10px;">
            <input type="text" name="buscar" placeholder="Buscar por descripción...">
            <button type="submit" class="btn btn-editar">Buscar</button>
        </form>
        <a href="movimientos?accion=nuevo" class="btn btn-nuevo">+ Nuevo Movimiento</a>
    </div>

    <table>
        <tr>
            <th>Fecha</th>
            <th>Usuario</th>
            <th>Tipo</th>
            <th>Monto</th>
            <th>Descripción</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="m" items="${lista}">
            <tr>
                <td>${m.fecha}</td>
                <td>${m.usuario.nombre}</td>
                <td>${m.tipo}</td>
                <td>$${m.monto}</td>
                <td>${m.descripcion}</td>
                <td>
                    <a href="movimientos?accion=editar&id=${m.id}" class="btn btn-editar">Editar</a>
                    <a href="movimientos?accion=eliminar&id=${m.id}" class="btn btn-borrar" onclick="return confirm('¿Seguro?')">Borrar</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty lista}">
            <tr><td colspan="6" style="text-align: center;">No hay movimientos.</td></tr>
        </c:if>
    </table>
</div>
</body>
</html>