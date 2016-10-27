package com.hotel.dao;

import com.hotel.dao.exceptions.DaoException;
import com.hotel.entity.AbstractEntity;
import com.hotel.util.HibernateUtil;

import java.util.List;

/**
 * Created by Алексей on 03.10.2016.
 */
public interface AbstractDAO<T extends AbstractEntity> {
    static HibernateUtil util = HibernateUtil.getInstance();

    void save (T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(int id) throws DaoException;

    List<T> getAll() throws DaoException;
}