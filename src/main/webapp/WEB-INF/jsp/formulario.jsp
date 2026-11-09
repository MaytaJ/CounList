<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario Movimiento</title>
    <style>
        body { font-family: Arial; background: #f8f9fa; display: flex; justify-content: center; padding-top: 50px; }
        .card { background: white; padding: 30px; border-radius: 8px; width: 350px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        input, select { width: 100%; padding: 8px; margin: 8px 0; border: 1px solid #ccc; box-sizing: border-box; border-radius: 4px; }
        button { width: 100%; padding: 10px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; margin-top: 10px; }
        .volver { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #555; }
    </style>
</head>
<body>
<div class="card">
    <h2>${movimiento != null ? 'Editar' : 'Nuevo'} Movimiento</h2>
    <form action="movimientos" method="POST">
        <input type="hidden" name="id" value="${movimiento.id}">

        <label>Tipo:</label>
        <select name="tipo">
            <option value="INGRESO" ${movimiento.tipo == 'INGRESO' ? 'selected' : ''}>Ingreso</option>
            <option value="GASTO" ${movimiento.tipo == 'GASTO' ? 'selected' : ''}>Gasto</option>
        </select>

        <label>Monto:</label>
        <input type="number" step="0.01" name="monto" value="${movimiento.monto}" required>

        <label>Fecha:</label>
        <input type="date" name="fecha" value="${movimiento.fecha}" required>

        <label>Descripción:</label>
        <input type="text" name="descripcion" value="${movimiento.descripcion}" required>

        <label>Categoría:</label>
        <input type="text" name="categoria" value="${movimiento.categoria}">

        <button type="submit">Guardar</button>
        <a href="movimientos" class="volver">Cancelar y volver</a>
    </form>
</div>
</body>
</html>