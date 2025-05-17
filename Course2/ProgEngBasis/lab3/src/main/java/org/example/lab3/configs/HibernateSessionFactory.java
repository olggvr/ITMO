package org.example.lab3.configs;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.example.lab3.entity.Result;

/**
 * Provides Hibernate session and configures Hibernate basing on hibernate.cfg.xml configuration file.
 *
 */
public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {}

    /**
     * @return sessionFactory, gives ability to work with db
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(Result.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
        return sessionFactory;
    }
}

