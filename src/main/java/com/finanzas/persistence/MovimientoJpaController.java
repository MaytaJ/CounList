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
        } finally {
            em.close();
        }
    }

    public void edit(Movimiento movimiento) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(movimiento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void remove(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Movimiento m = em.find(Movimiento.class, id);
            if (m != null) em.remove(m);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Movimiento find(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Movimiento.class, id);
        } finally {
            em.close();
        }
    }

    public List<Movimiento> findByUsuario(Long idUsuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT m FROM Movimiento m WHERE m.usuario.id = :id", Movimiento.class)
                    .setParameter("id", idUsuario)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Movimiento> buscarPorDescripcion(Long idUsuario, String texto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT m FROM Movimiento m WHERE m.usuario.id = :id AND LOWER(m.descripcion) LIKE LOWER(:texto)", Movimiento.class)
                    .setParameter("id", idUsuario)
                    .setParameter("texto", "%" + texto + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}