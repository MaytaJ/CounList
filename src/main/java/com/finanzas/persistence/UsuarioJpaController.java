package com.finanzas.persistence;

import com.finanzas.model.Usuario;
import com.finanzas.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class UsuarioJpaController {

    public void create(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally { em.close(); }
    }

    public void edit(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } finally { em.close(); }
    }

    public void remove(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario u = em.find(Usuario.class, id);
            if (u != null) em.remove(u);
            em.getTransaction().commit();
        } finally { em.close(); }
    }

    public Usuario find(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally { em.close(); }
    }

    public List<Usuario> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally { em.close(); }
    }
}