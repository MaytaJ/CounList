package com.finanzas.logic;

import com.finanzas.model.Movimiento;
import com.finanzas.persistence.MovimientoJpaController;
import java.util.List;

public class MovimientoControladora {
    private MovimientoJpaController jpa = new MovimientoJpaController();

    public void crearMovimiento(Movimiento m) {
        jpa.create(m);
    }

    public List<Movimiento> listarMovimientos(Long idUsuario) {
        return jpa.findByUsuario(idUsuario);
    }

    public Movimiento traerMovimiento(Long id) {
        return jpa.find(id);
    }

    public void modificarMovimiento(Movimiento m) {
        jpa.edit(m);
    }

    public void eliminarMovimiento(Long id) {
        jpa.remove(id);
    }

    public List<Movimiento> buscarMovimientos(Long idUsuario, String texto) {
        return jpa.buscarPorDescripcion(idUsuario, texto);
    }
}