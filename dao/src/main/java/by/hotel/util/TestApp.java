package by.hotel.util;

import by.hotel.entity.BookingEntity;
import by.hotel.entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

/**
 * Created by Алексей on 16.10.2016.
 */
public class TestApp {

    public static void main(String[] args) {
        System.out.println("Hibernate one to one (Annotation)");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        UserEntity userEntity = new UserEntity();
        String hql = "SELECT U FROM UserEntity U WHERE U.userId=24";
        Query query = session.createQuery(hql);
        List<UserEntity> users = query.list();
        userEntity = (UserEntity) query.uniqueResult();
        System.out.println(userEntity);
        int userId = userEntity.getUserId();
        System.out.println(userId);



        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setPlace(1);
        bookingEntity.setCategory("lux");
        bookingEntity.setStartDate(new Date());
        bookingEntity.setEndDate(new Date());
        bookingEntity.setStatus("new");
        bookingEntity.setUserEntity(userEntity);
      //  UserEntity user = new UserEntity();
        //   user.setUserId(333);
        // session.save(user);


        session.save(bookingEntity);


        //AccountEntity accountEntity = new AccountEntity();

        //accountEntity.setSumma(111);
        //session.save(accountEntity);
        session.getTransaction().commit();
        System.out.println("Done");


    }
}
