package by.hotel.dao;

import by.hotel.dao.exceptions.DaoException;
import by.hotel.entity.AbstractEntity;

import java.util.List;

/**
 * Created by Алексей on 03.10.2016.
 */
public interface AbstractDAO<T extends AbstractEntity> {
    void create(T entity) throws DaoException;
    void update(T entity) throws DaoException;
    void delete(int id) throws DaoException;
    List<T> getAll () throws DaoException;
}