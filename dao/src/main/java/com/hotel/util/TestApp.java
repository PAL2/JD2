package com.hotel.util;

import com.hotel.entity.BookingEntity;
import com.hotel.entity.Room;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static com.hotel.dao.AbstractDAO.util;

/**
 * Created by Алексей on 16.10.2016.
 */
public class TestApp {

    public static void main(String[] args) {
        /*System.out.println("Hibernate one to one (Annotation)");
        Session session = HibernateUtil.getInstance().getSession();
        session.beginTransaction();

        String hql = "SELECT U FROM User U WHERE U.userId=24";
        Query query = session.createQuery(hql);
        User userEntity = (User) query.uniqueResult();

        String hql2 = "SELECT R FROM Room R WHERE R.roomId=2";
        Query query1 = session.createQuery(hql2);
        Room room = (Room) query1.uniqueResult();

        String hql3 = "SELECT A FROM AccountEntity A WHERE A.accountId=27";
        Query query2 = session.createQuery(hql3);
        AccountEntity accountEntity = (AccountEntity) query2.uniqueResult();

        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setPlace(1);
        bookingEntity.setCategory("lux");
        bookingEntity.setStartDate(new Date());
        bookingEntity.setEndDate(new Date());
        bookingEntity.setStatus("new");
        bookingEntity.setUser(userEntity);
        bookingEntity.setRoom(room);
        bookingEntity.setAccountEntity(accountEntity);

        session.save(bookingEntity);

        session.getTransaction().commit();*/
        //System.out.println("Done");
        /*HibernateUtil util = HibernateUtil.getInstance();
        List<Room> rooms;
        Session session = util.getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Room ");
        rooms = query.list();
        System.out.println(rooms);
        transaction.commit();*/
       /* BookingEntity booking;
        Room room;
        try {

            Session session = util.getSession();
            session.beginTransaction();
            booking = (BookingEntity) session.get(BookingEntity.class, 62);
            room = (Room) session.get(Room.class, booking.getRoomId());
            Date startDate = booking.getStartDate();
            Date endDate = booking.getEndDate();
            long st = startDate.getTime();
            long en = endDate.getTime();
            System.out.println((en - st) / 24 / 60 / 60 / 1000);
            AccountEntity account = new AccountEntity();
            System.out.println((int) ((en - st) / 24 / 60 / 60 / 1000) * room.getPrice());
            //account.setSumma((int) ((en - st) / 24 / 60 / 60 / 1000)*room.getPrice());
            account.setSumma(123);
          //  booking.setAccountId(43);
            account.setBookingEntity(booking);
            booking.setAccountEntity(account);
            session.save(account);
            session.save(booking);
            session.close();
            System.out.println(booking);
        } catch (HibernateException e) {

        }*/

        Room room;
        BookingEntity booking;
        Session session = util.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE BookingEntity B SET B.roomId=13, B.status=12 WHERE B.bookingId=62");
        int q = query.executeUpdate();
        System.out.println(q);
        // query.setParameter(0, userId);
        // accounts = query.list();
        // LOG.info(accounts);
           /* booking = (BookingEntity) session.get(BookingEntity.class, 62);
            System.out.println(booking);
            transaction.commit();
            session.flush();
            transaction = session.beginTransaction();
            System.out.println();
            System.out.println(booking.getRoomId());
            room = (Room) session.get(Room.class, booking.getRoomId());
            Date startDate = booking.getStartDate();
            Date endDate = booking.getEndDate();
            long st = startDate.getTime();
            long en = endDate.getTime();

            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setSumma((int) ((en - st) / 24 / 60 / 60 / 1000) * room.getPrice());

            System.out.println(booking);
            booking.setStatus("billed");

            accountEntity.setBookingEntity(booking);
            booking.setAccountEntity(accountEntity);

            session.save(booking);
            session.save(accountEntity);*/
        transaction.commit();

    }
}
