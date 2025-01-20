package org.example.lab3.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import org.example.lab3.configs.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.example.lab3.entity.Result;

import java.util.List;

@ApplicationScoped
public class ResultRepository {

    private final EntityManagerFactory entityManagerFactory;

    public ResultRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    public void save(Result result) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try (session) {
            session.persist(result);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Exception: " + e);
            tx.rollback();
        }

    }

    public List<Result> findAll() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("from Result", Result.class).list();
        }
    }

    public void clean() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try (em) {
            transaction.begin();
            em.createQuery("DELETE FROM Result").executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
        }
    }
}

