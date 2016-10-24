package com.hotel.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

import static com.hotel.dao.AbstractDAO.util;

/**
 * Created by Алексей on 16.10.2016.
 */
public class TestApp {

    public static void main(String[] args) {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        transaction.commit();
    }
}
