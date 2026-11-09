package com.finanzas.web;

import com.finanzas.logic.UsuarioControladora;
import com.finanzas.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsuarioControladora controladora = new UsuarioControladora();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("registrar".equals(accion)) {
            try {
                Usuario nuevo = new Usuario(
                        request.getParameter("username"),
                        request.getParameter("password"),
                        request.getParameter("nombre")
                );
                controladora.crearUsuario(nuevo);
                request.setAttribute("exito", "Usuario registrado. ¡Iniciá sesión!");
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }

        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        Usuario logueado = controladora.login(user, pass);

        if (logueado != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", logueado);
            response.sendRedirect("movimientos");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}