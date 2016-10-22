package by.hotel.dao;

import by.hotel.entity.UserEntity;
import org.junit.Before;

/**
 * Created by Алексей on 06.10.2016.
 */
public class UserDAOImplTest {
    private UserEntity expected;

    @Before
    public void setExpected() {
        expected = new UserEntity("Алексей", "Петров", "admin", "pal", "1234");
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
