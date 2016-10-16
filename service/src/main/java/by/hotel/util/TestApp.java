package by.hotel.util;

import by.hotel.entity.AccountEntity;
import org.hibernate.Session;

/**
 * Created by Алексей on 16.10.2016.
 */
public class TestApp {
    public static void main(String[] args) {
        System.out.println("Hibernate one to one (Annotation)");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setSumma(999);
        session.save(accountEntity);
        session.getTransaction().commit();
        System.out.println("Done");


    }
}
