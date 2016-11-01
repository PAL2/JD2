package com.hotel.dao;

import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.AbstractEntity;
import com.hotel.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Алексей on 03.10.2016.
 */
public abstract class AbstractDAO<T extends AbstractEntity> implements DAO<T> {
    protected static HibernateUtil util = HibernateUtil.getInstance();
    private final Logger LOG = Logger.getLogger(AbstractDAO.class);
    private Class pClass;

    protected AbstractDAO(Class pClass) {
        this.pClass = pClass;
    }


    @Override
    public void save(T entity) throws DaoException {
        try {
            Session session = util.getSession();
            session.saveOrUpdate(entity);
        } catch (HibernateException e) {
            LOG.error("Error in DAO");
            throw new DaoException();
        }
    }

    @Override
    public void update(T entity) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            Session session = util.getSession();
            T account = (T) session.get(pClass, id);
            session.delete(account);
        } catch (HibernateException e) {
            e.printStackTrace();
            LOG.error("Unable to delete the book. Error in DAO");
            throw new DaoException();
        }

    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> results;
        try {
            Session session = util.getSession();
            Criteria criteria = session.createCriteria(pClass);
            results = criteria.list();
            LOG.info(results);
        } catch (HibernateException e) {
            LOG.error("Unable to create a list of accounts. Error in DAO");
            throw new DaoException();
        }
        return results;
    }
}