package com.finanzas.logic;

import com.finanzas.model.Movimiento;
import com.finanzas.persistence.MovimientoJpaController;
import java.util.List;

public class MovimientoControladora {
    private MovimientoJpaController jpa = new MovimientoJpaController();

    public void crearMovimiento(Movimiento m) {
        jpa.create(m);
    }

    public List<Movimiento> listarMovimientos(String busqueda) {
        if (busqueda != null && !busqueda.trim().isEmpty()) {
            return jpa.buscarPorDescripcion(busqueda);
        }
        return jpa.findAll();
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
}