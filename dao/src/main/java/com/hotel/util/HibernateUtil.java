package com.hotel.util;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Алексей on 16.10.2016.
 */
public class HibernateUtil {

    private final Logger LOG = Logger.getLogger(HibernateUtil.class);
    private static HibernateUtil util;
    private SessionFactory sessionFactory;
    private final ThreadLocal<Session> sessions = new ThreadLocal<>();

    private HibernateUtil() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            LOG.error("Initial session factory creation failed ", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static HibernateUtil getInstance() {
        if (util == null) {
            util = new HibernateUtil();
        }
        return util;
    }

    public Session getSession() {
        Session session = sessions.get();
        if (session == null) {
            session = sessionFactory.openSession();
            sessions.set(session);
        }
        return session;
    }

    public void releaseSession(Session session) {
        if (session != null) {
            try {
                sessions.remove();
            } catch (HibernateException e) {
                LOG.error(e);
            }
        }
    }
}