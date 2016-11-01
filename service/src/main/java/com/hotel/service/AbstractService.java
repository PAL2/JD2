package com.hotel.service;

import com.hotel.entity.AbstractEntity;
import com.hotel.util.HibernateUtil;

/**
 * Created by Алексей on 01.10.2016.
 */
public abstract class AbstractService<T extends AbstractEntity> {
    protected static HibernateUtil util = HibernateUtil.getInstance();
}
