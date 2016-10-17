package by.hotel.util;

import by.hotel.entity.AccountEntity;
import by.hotel.entity.BookingEntity;
import org.hibernate.Session;

import java.util.Date;

/**
 * Created by Алексей on 16.10.2016.
 */
public class TestApp {
    public static void main(String[] args) {
        System.out.println("Hibernate one to one (Annotation)");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

      //  BookingEntity bookingEntity = new BookingEntity();
        //bookingEntity.setStartDate(new Date());
        //bookingEntity.setEndDate(new Date());
        //bookingEntity.setPlace(1);
        //bookingEntity.setCategory("lux");
      //  bookingEntity.setUserId(1);
        //bookingEntity.setStatus("bbh");

        //session.save(bookingEntity);


        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setSumma(111);
        session.save(accountEntity);
        session.getTransaction().commit();
        System.out.println("Done");


    }
}
