package com.finanzas.logic;

import com.finanzas.model.Movimiento;
import com.finanzas.model.Usuario;
import com.finanzas.persistence.MovimientoJpaController;
import com.finanzas.persistence.UsuarioJpaController;
import java.util.List;

public class UsuarioControladora {
    private UsuarioJpaController usuarioJpa = new UsuarioJpaController();
    private MovimientoJpaController movimientoJpa = new MovimientoJpaController();

    public void crearUsuario(Usuario u) throws Exception {
        if (u.getUsername() == null || u.getUsername().trim().isEmpty()) {
            throw new Exception("El nombre de usuario no puede estar vacío.");
        }
        usuarioJpa.create(u);
    }

    public List<Usuario> traerUsuarios() {
        return usuarioJpa.findAll();
    }

    public Usuario traerUsuario(Long id) {
        return usuarioJpa.find(id);
    }

    public void modificarUsuario(Usuario u) {
        usuarioJpa.edit(u);
    }

    public void eliminarUsuario(Long id) throws Exception {
        List<Movimiento> movs = new MovimientoJpaController().findAll();
        boolean tieneMovimientos = movs.stream().anyMatch(m -> m.getUsuario().getId().equals(id));

        if (tieneMovimientos) {
            throw new Exception("No se puede eliminar el usuario porque tiene movimientos registrados.");
        }
        usuarioJpa.remove(id);
    }
}