package com.hotel.service;

import com.hotel.util.HibernateUtil;

/**
 * Created by Алексей on 01.10.2016.
 */
public abstract class AbstractService {
    protected static HibernateUtil util = HibernateUtil.getInstance();
}
