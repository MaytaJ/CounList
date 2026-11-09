<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Acceso - Finanzas</title>
    <style>
        body {
            font-family: Arial;
            background: #e9ecef;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 350px;
            overflow: hidden;
        }

        .tabs {
            display: flex;
            background: #f1f3f5;
        }

        .tab {
            flex: 1;
            text-align: center;
            padding: 15px;
            cursor: pointer;
            font-weight: bold;
            color: #666;
            border-bottom: 2px solid transparent;
        }

        .tab.active {
            background: white;
            color: #007bff;
            border-bottom: 2px solid #007bff;
        }

        .content {
            padding: 30px;
            display: none;
        }

        .content.active {
            display: block;
        }

        input {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            box-sizing: border-box;
            border-radius: 4px;
        }

        button {
            width: 100%;
            padding: 10px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
            font-weight: bold;
        }

        .btn-registro {
            background: #28a745;
        }

        .msg {
            color: red;
            text-align: center;
            margin-bottom: 10px;
            font-size: 13px;
        }

        .exito {
            color: green;
            text-align: center;
            margin-bottom: 10px;
            font-size: 13px;
        }
    </style>
</head>
<body>
<div class="card">
    <div class="tabs">
        <div class="tab active" onclick="cambiarTab('login')">Ingresar</div>
        <div class="tab" onclick="cambiarTab('registro')">Registrarse</div>
    </div>

    <div style="padding: 0 30px;">
        <% if (request.getAttribute("error") != null) { %>
        <div class="msg" style="margin-top: 15px;"><%= request.getAttribute("error") %>
        </div>
        <% } %>
        <% if (request.getAttribute("exito") != null) { %>
        <div class="exito" style="margin-top: 15px;"><%= request.getAttribute("exito") %>
        </div>
        <% } %>
    </div>

    <div id="content-login" class="content active">
        <form action="login" method="POST">
            <input type="hidden" name="accion" value="login">
            <input type="text" name="username" placeholder="Usuario" required>
            <input type="password" name="password" placeholder="Contraseña" required>
            <button type="submit">Entrar</button>
        </form>
    </div>

    <div id="content-registro" class="content">
        <form action="login" method="POST">
            <input type="hidden" name="accion" value="registrar">
            <input type="text" name="username" placeholder="Nuevo Usuario" required>
            <input type="password" name="password" placeholder="Contraseña" required>
            <input type="text" name="nombre" placeholder="Tu Nombre" required>
            <button type="submit" class="btn-registro">Crear Cuenta</button>
        </form>
    </div>
</div>

<script>
    function cambiarTab(tab) {
        document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
        document.querySelectorAll('.content').forEach(c => c.classList.remove('active'));
        if (tab === 'login') {
            document.querySelectorAll('.tab')[0].classList.add('active');
            document.getElementById('content-login').classList.add('active');
        } else {
            document.querySelectorAll('.tab')[1].classList.add('active');
            document.getElementById('content-registro').classList.add('active');
        }
    }
</script>
</body>
</html>