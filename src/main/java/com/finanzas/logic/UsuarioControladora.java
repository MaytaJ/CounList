package com.finanzas.logic;

import com.finanzas.model.Usuario;
import com.finanzas.persistence.MovimientoJpaController;
import com.finanzas.persistence.UsuarioJpaController;

import java.util.List;

public class UsuarioControladora {
    private UsuarioJpaController usuarioJpa = new UsuarioJpaController();
    private MovimientoJpaController movimientoJpa = new MovimientoJpaController();

    public void crearUsuario(Usuario u) throws Exception {
        if (usuarioJpa.findByUsername(u.getUsername()) != null) {
            throw new Exception("El nombre de usuario ya está en uso.");
        }
        usuarioJpa.create(u);
    }

    public Usuario login(String user, String pass) {
        Usuario u = usuarioJpa.findByUsername(user);
        if (u != null && u.getPassword().equals(pass)) return u;
        return null;
    }

    public void modificarUsuario(Usuario u) {
        usuarioJpa.edit(u);
    }

    public void eliminarUsuario(Long id) throws Exception {
        List<?> movs = movimientoJpa.findByUsuario(id);
        if (movs != null && !movs.isEmpty()) {
            throw new Exception("No podés eliminar tu cuenta porque tenés movimientos registrados.");
        }
        usuarioJpa.remove(id);
    }
}