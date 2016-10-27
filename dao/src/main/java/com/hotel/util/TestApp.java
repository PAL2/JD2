package com.hotel.util;

import com.hotel.entity.Booking;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.hotel.dao.AbstractDAO.util;

/**
 * Created by Алексей on 16.10.2016.
 */
public class TestApp {

    public static void main(String[] args) {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        int id = 91;
        Booking booking = (Booking) session.get(Booking.class, id);
        session.delete(booking);
        transaction.commit();
    }
}
