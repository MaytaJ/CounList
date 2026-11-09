<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mi Perfil</title>
    <style>
        body {
            font-family: Arial;
            background: #f8f9fa;
            display: flex;
            justify-content: center;
            padding-top: 50px;
        }

        .card {
            background: white;
            padding: 30px;
            border-radius: 8px;
            width: 350px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        input {
            width: 100%;
            padding: 8px;
            margin: 8px 0;
            border: 1px solid #ccc;
            box-sizing: border-box;
            border-radius: 4px;
        }

        .btn-guardar {
            width: 100%;
            padding: 10px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }

        .btn-borrar {
            width: 100%;
            padding: 10px;
            background: #dc3545;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 20px;
        }

        .volver {
            display: block;
            text-align: center;
            margin-top: 15px;
            text-decoration: none;
            color: #555;
        }

        .msg {
            color: red;
            text-align: center;
            font-size: 14px;
            margin-bottom: 10px;
        }

        .exito {
            color: green;
            text-align: center;
            font-size: 14px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="card">
    <h2 style="text-align: center;">Mi Perfil</h2>

    <% if (request.getAttribute("error") != null) { %>
    <div class="msg"><%= request.getAttribute("error") %>
    </div>
    <% } %>
    <% if (request.getAttribute("exito") != null) { %>
    <div class="exito"><%= request.getAttribute("exito") %>
    </div>
    <% } %>

    <form action="perfil" method="POST">
        <input type="hidden" name="accion" value="editar">
        <label>Usuario (No modificable):</label>
        <input type="text" value="${user.username}" disabled style="background:#eee;">
        <label>Nombre:</label>
        <input type="text" name="nombre" value="${user.nombre}" required>
        <label>Contraseña:</label>
        <input type="password" name="password" value="${user.password}" required>
        <button type="submit" class="btn-guardar">Guardar Cambios</button>
    </form>

    <hr style="margin: 20px 0; border: 0; border-top: 1px solid #eee;">

    <form action="perfil" method="POST"
          onsubmit="return confirm('¿Estás seguro de eliminar tu cuenta? Esto es irreversible.');">
        <input type="hidden" name="accion" value="eliminar">
        <button type="submit" class="btn-borrar">Eliminar mi cuenta</button>
    </form>

    <a href="movimientos" class="volver">Volver a mis movimientos</a>
</div>
</body>
</html>