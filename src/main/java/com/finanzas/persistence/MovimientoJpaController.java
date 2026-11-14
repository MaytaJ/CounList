package com.finanzas.persistence;

import com.finanzas.model.Movimiento;
import com.finanzas.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MovimientoJpaController {

    public void create(Movimiento movimiento) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movimiento);
            em.getTransaction().commit();
        } finally { em.close(); }
    }

    public void edit(Movimiento movimiento) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(movimiento);
            em.getTransaction().commit();
        } finally { em.close(); }
    }

    public void remove(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Movimiento m = em.find(Movimiento.class, id);
            if (m != null) em.remove(m);
            em.getTransaction().commit();
        } finally { em.close(); }
    }

    public Movimiento find(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Movimiento.class, id);
        } finally { em.close(); }
    }

    public List<Movimiento> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT m FROM Movimiento m", Movimiento.class).getResultList();
        } finally { em.close(); }
    }

    public List<Movimiento> buscarPorDescripcion(String texto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT m FROM Movimiento m WHERE LOWER(m.descripcion) LIKE LOWER(:texto)", Movimiento.class)
                    .setParameter("texto", "%" + texto + "%")
                    .getResultList();
        } finally { em.close(); }
    }
}