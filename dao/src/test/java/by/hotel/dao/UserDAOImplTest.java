package by.hotel.dao;

import by.hotel.entity.User;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Алексей on 06.10.2016.
 */
public class UserDAOImplTest {
    private User expected;

    @Before
    public void setExpected() {
        expected = new User(1, "Алексей", "Петров", "admin", "pal", "1234");
    }

    public void tearDown() {
        expected = null;
    }

 //   @Test
   // public void testCreate() throws Exception{
     //   UserDAOImpl.getInstance().create(expected);
       // User actual = UserDAOImpl.getInstance()

    //}

}
