<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario Usuario</title>
    <style>
        body { font-family: Arial; background: #f8f9fa; display: flex; justify-content: center; padding-top: 50px; }
        .card { background: white; padding: 30px; border-radius: 8px; width: 350px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        input { width: 100%; padding: 8px; margin: 8px 0; border: 1px solid #ccc; box-sizing: border-box; border-radius: 4px; }
        button { width: 100%; padding: 10px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; margin-top: 10px; }
        .volver { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #555; }
        .msg { color: red; text-align: center; font-size: 14px; margin-bottom: 10px; }
    </style>
</head>
<body>
<div class="card">
    <h2>${usuario != null ? 'Editar' : 'Nuevo'} Usuario</h2>

    <% if (request.getAttribute("error") != null) { %> <div class="msg"><%= request.getAttribute("error") %></div> <% } %>

    <form action="usuarios" method="POST">
        <input type="hidden" name="id" value="${usuario.id}">
        <label>Nombre de Usuario:</label>
        <input type="text" name="username" value="${usuario.username}" required>
        <label>Contraseña:</label>
        <input type="text" name="password" value="${usuario.password}" required>
        <label>Nombre Real:</label>
        <input type="text" name="nombre" value="${usuario.nombre}" required>
        <button type="submit">Guardar</button>
        <a href="usuarios" class="volver">Cancelar</a>
    </form>
</div>
</body>
</html>