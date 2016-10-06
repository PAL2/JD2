package by.hotel.dao;

import by.hotel.entity.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Алексей on 05.10.2016.
 */
public class AccountDAOImplTest {
    private Account expected;

    @Before
    public void setUp() {
        expected = new Account(1, 10000);
    }

    @After
    public void tearDown() {
        expected = null;
    }

    @Test
    public void testGetInstance() throws Exception {
        AccountDAOImpl instance1 = AccountDAOImpl.getInstance();
        AccountDAOImpl instance2 = AccountDAOImpl.getInstance();
        assertEquals(instance1.hashCode(), instance2.hashCode());
    }

}
