package com.finanzas.web;

import com.finanzas.logic.UsuarioControladora;
import com.finanzas.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
    private UsuarioControladora controladora = new UsuarioControladora();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        try {
            switch (accion) {
                case "nuevo":
                    request.getRequestDispatcher("/WEB-INF/jsp/formularioUsuario.jsp").forward(request, response);
                    break;
                case "editar":
                    Long id = Long.parseLong(request.getParameter("id"));
                    request.setAttribute("usuario", controladora.traerUsuario(id));
                    request.getRequestDispatcher("/WEB-INF/jsp/formularioUsuario.jsp").forward(request, response);
                    break;
                case "eliminar":
                    Long idEliminar = Long.parseLong(request.getParameter("id"));
                    controladora.eliminarUsuario(idEliminar);
                    response.sendRedirect("usuarios");
                    break;
                default:
                    request.setAttribute("lista", controladora.traerUsuarios());
                    request.getRequestDispatcher("/WEB-INF/jsp/listaUsuarios.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("lista", controladora.traerUsuarios());
            request.getRequestDispatcher("/WEB-INF/jsp/listaUsuarios.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");

        try {
            if (idStr == null || idStr.isEmpty()) {
                Usuario nuevo = new Usuario(username, password, nombre);
                controladora.crearUsuario(nuevo);
            } else {
                Usuario u = controladora.traerUsuario(Long.parseLong(idStr));
                u.setUsername(username);
                u.setPassword(password);
                u.setNombre(nombre);
                controladora.modificarUsuario(u);
            }
            response.sendRedirect("usuarios");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/formularioUsuario.jsp").forward(request, response);
        }
    }
}