package com.hotel.service;

import com.hotel.entity.AbstractEntity;
import com.hotel.service.exceptions.ServiceException;

import java.util.List;

/**
 * Created by Алексей on 01.11.2016.
 */
public interface Service<T extends AbstractEntity> {
    void save(T entity) throws ServiceException;

    void update(T entity) throws ServiceException;

    void delete(int id) throws ServiceException;

    List<T> getAll() throws ServiceException;


}
