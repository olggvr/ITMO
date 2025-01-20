package org.example.lab3.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.lab3.configs.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.example.lab3.entity.Result;

import java.util.List;

public class ResultRepository {

    private final EntityManagerFactory entityManagerFactory;

    public ResultRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    public void save(Result result) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(result);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Exception: " + e);
            tx.rollback();
        } finally {
            session.close();
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
        try {
            transaction.begin();
            em.createQuery("DELETE FROM Result").executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}

