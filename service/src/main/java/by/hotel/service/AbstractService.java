package by.hotel.service;

import by.hotel.util.HibernateUtil;

import java.sql.Connection;

/**
 * Created by Алексей on 01.10.2016.
 */
public abstract class AbstractService {
    protected Connection conn;
    protected static HibernateUtil util = HibernateUtil.getInstance();
}
