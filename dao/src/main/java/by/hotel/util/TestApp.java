package by.hotel.util;

/**
 * Created by Алексей on 16.10.2016.
 */
public class TestApp {

    public static void main(String[] args) {
        /*System.out.println("Hibernate one to one (Annotation)");
        Session session = HibernateUtil.getInstance().getSession();
        session.beginTransaction();

        String hql = "SELECT U FROM UserEntity U WHERE U.userId=24";
        Query query = session.createQuery(hql);
        UserEntity userEntity = (UserEntity) query.uniqueResult();

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
        bookingEntity.setUserEntity(userEntity);
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
    }
}
