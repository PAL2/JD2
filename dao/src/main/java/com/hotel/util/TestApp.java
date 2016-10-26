package com.hotel.util;

import com.hotel.entity.Room;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import static com.hotel.dao.AbstractDAO.util;

/**
 * Created by Алексей on 16.10.2016.
 */
public class TestApp {

    public static void main(String[] args) {
        Long amount;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Room.class);
        criteria.setProjection(Projections.rowCount());
        amount = (Long) criteria.uniqueResult();
        System.out.println(amount);
        transaction.commit();
    }
}
