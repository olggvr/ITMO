package org.example.lab3.repository;

import org.example.lab3.configs.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.example.lab3.entity.Result;

import java.util.List;

public class ResultRepository {

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
}

