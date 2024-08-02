package ru.bozhov.decemberCloud.common.utils;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;


@Service
public class HibernateUtil {
    private EntityManagerFactory entityManagerFactory;
    private SessionFactory sessionFactory;
    private static HibernateUtil instance;


    public HibernateUtil( EntityManagerFactory entityManagerFactory) {
        instance = this;
        this.entityManagerFactory = entityManagerFactory;
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }
    public static HibernateUtil getInstance() {
        return instance;
    }
    private Session _getSession() {
        Session s = sessionFactory.openSession();
        s.setCacheMode(CacheMode.REFRESH);

        return s;
    }
    public static Session getSession() {
        return instance._getSession();
    }
}
