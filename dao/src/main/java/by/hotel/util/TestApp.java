package by.hotel.util;

import by.hotel.entity.AccountEntity;
import by.hotel.entity.BookingEntity;
import by.hotel.entity.RoomEntity;
import by.hotel.entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

import static by.hotel.dao.AbstractDAO.util;

/**
 * Created by Алексей on 16.10.2016.
 */
public class TestApp {

    public static void main(String[] args) {
        System.out.println("Hibernate one to one (Annotation)");
        Session session = util.getSession();
        session.beginTransaction();

      //  List<RoomEntity> rooms;

        //Query query = session.createQuery("from RoomEntity ");
        //rooms = query.list();
      //  System.out.println(rooms);

        String hql = "SELECT U FROM UserEntity U WHERE U.userId=24";
        Query query = session.createQuery(hql);
        UserEntity userEntity = (UserEntity) query.uniqueResult();

        String hql2 = "SELECT R FROM RoomEntity R WHERE R.roomId=2";
        Query query1 = session.createQuery(hql2);
        RoomEntity roomEntity = (RoomEntity) query1.uniqueResult();

        String hql3 = "SELECT A FROM AccountEntity A WHERE A.accountId=27";
        Query query2 = session.createQuery(hql3);
        AccountEntity accountEntity = (AccountEntity) query2.uniqueResult();

        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setPlace(1);
        bookingEntity.setCategory("lux");
        bookingEntity.setStartDate(new Date());
        bookingEntity.setEndDate(new Date());
        bookingEntity.setStatus("new");
        bookingEntity.setUserEntity(userEntity);
        bookingEntity.setRoomEntity(roomEntity);
        bookingEntity.setAccountEntity(accountEntity);

        session.save(bookingEntity);

        session.getTransaction().commit();
        System.out.println("Done");


    }
}
