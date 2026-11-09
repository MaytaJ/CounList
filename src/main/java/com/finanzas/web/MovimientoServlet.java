package com.finanzas.web;

import com.finanzas.logic.MovimientoControladora;
import com.finanzas.model.Movimiento;
import com.finanzas.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet("/movimientos")
public class MovimientoServlet extends HttpServlet {
    private MovimientoControladora controladora = new MovimientoControladora();

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
        Usuario u = getUsuario(request, response);
        if (u == null) return;

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "nuevo":
                request.getRequestDispatcher("/WEB-INF/jsp/formulario.jsp").forward(request, response);
                break;
            case "editar":
                Long id = Long.parseLong(request.getParameter("id"));
                request.setAttribute("movimiento", controladora.traerMovimiento(id));
                request.getRequestDispatcher("/WEB-INF/jsp/formulario.jsp").forward(request, response);
                break;
            case "eliminar":
                Long idEliminar = Long.parseLong(request.getParameter("id"));
                controladora.eliminarMovimiento(idEliminar);
                response.sendRedirect("movimientos");
                break;
            default:
                String busqueda = request.getParameter("buscar");
                if (busqueda != null && !busqueda.trim().isEmpty()) {
                    request.setAttribute("lista", controladora.buscarMovimientos(u.getId(), busqueda));
                } else {
                    request.setAttribute("lista", controladora.listarMovimientos(u.getId()));
                }
                request.getRequestDispatcher("/WEB-INF/jsp/lista.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario u = getUsuario(request, response);
        if (u == null) return;

        String idStr = request.getParameter("id");
        String tipo = request.getParameter("tipo");
        BigDecimal monto = new BigDecimal(request.getParameter("monto"));
        LocalDate fecha = LocalDate.parse(request.getParameter("fecha"));
        String descripcion = request.getParameter("descripcion");
        String categoria = request.getParameter("categoria");

        Movimiento m = new Movimiento();
        m.setTipo(tipo);
        m.setMonto(monto);
        m.setFecha(fecha);
        m.setDescripcion(descripcion);
        m.setCategoria(categoria);
        m.setUsuario(u);

        if (idStr == null || idStr.isEmpty()) {
            controladora.crearMovimiento(m);
        } else {
            m.setId(Long.parseLong(idStr));
            controladora.modificarMovimiento(m);
        }

        response.sendRedirect("movimientos");
    }
}