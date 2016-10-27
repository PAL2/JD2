package com.hotel.util;

import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.entity.User;
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
    /*private static AccountServiceImpl accountService;
    private static BookingServiceImpl bookingService;
    private static RoomServiceImpl roomService;
    private static UserServiceImpl userService;*/

    public static void main(String[] args) {
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        Room room = new Room("TEST", 33, 1000);
        Account expectedAccount = new Account(5000);
        User user = new User("TEST", "TEST", "client", "TEST", "TEST", null);
        GregorianCalendar calendar = new GregorianCalendar(2016, Calendar.OCTOBER, 30);
        GregorianCalendar calendar2 = new GregorianCalendar(2016, Calendar.NOVEMBER, 30);
        Date startDate = calendar.getTime();
        Date endDate = calendar2.getTime();
        Booking booking = new Booking(startDate, endDate, 1, "TEST", 1, 1, 1, "TEST", null, null, null);
        booking.setAccount(expectedAccount);
        booking.setRoom(room);
        booking.setUser(user);
        transaction.commit();
    }
}
