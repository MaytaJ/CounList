package com.finanzas.web;

import com.finanzas.logic.UsuarioControladora;
import com.finanzas.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/perfil")
public class PerfilServlet extends HttpServlet {
    private UsuarioControladora controladora = new UsuarioControladora();

    private Usuario getUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return null;
        }
        return (Usuario) session.getAttribute("user");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getUsuario(request, response) != null) {
            request.getRequestDispatcher("/WEB-INF/jsp/perfil.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario u = getUsuario(request, response);
        if (u == null) return;

        String accion = request.getParameter("accion");

        try {
            if ("editar".equals(accion)) {
                u.setNombre(request.getParameter("nombre"));
                u.setPassword(request.getParameter("password"));
                controladora.modificarUsuario(u);
                request.setAttribute("exito", "Datos actualizados correctamente.");
            } else if ("eliminar".equals(accion)) {
                controladora.eliminarUsuario(u.getId());
                request.getSession().invalidate();
                response.sendRedirect("login");
                return;
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher("/WEB-INF/jsp/perfil.jsp").forward(request, response);
    }
}