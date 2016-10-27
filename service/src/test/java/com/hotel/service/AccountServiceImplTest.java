package com.hotel.service;

import com.hotel.entity.Account;
import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.entity.User;
import com.hotel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Алексей on 26.10.2016.
 */
public class AccountServiceImplTest {
    private static AccountServiceImpl accountService;
    private static BookingServiceImpl bookingService;
    private static RoomServiceImpl roomService;
    private static UserServiceImpl userService;
    private Account expectedAccount;
    private Account actualAccount;
    private Booking booking;
    private Room room;
    private User user;
    private Integer accountId;
    private Integer bookingId;
    private Integer roomId;
    private Integer userId;

    @BeforeClass
    public static void initTest() {
        accountService = AccountServiceImpl.getInstance();
        bookingService = BookingServiceImpl.getInstance();
        roomService = RoomServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
    }

    @Before
    public void setUp() throws Exception {
        expectedAccount = new Account(5000, booking);
        user = new User("TEST", "TEST", "client", "TEST", "TEST", null);
        GregorianCalendar calendar = new GregorianCalendar(2016, Calendar.OCTOBER, 30);
        GregorianCalendar calendar2 = new GregorianCalendar(2016, Calendar.NOVEMBER, 30);
        Date startDate = calendar.getTime();
        Date endDate = calendar2.getTime();
        booking = new Booking(startDate, endDate, 1, "TEST", 1, 1, 1, "TEST", null, null, null);
        booking.setAccount(expectedAccount);
        booking.setUser(user);
        persistEntities();
    }

    @Test
    public void getAllAccountById() throws Exception {


    }

    @After
    public void tearDown() throws Exception {
        expectedAccount = null;
        actualAccount = null;
        booking = null;
        room = null;
        user = null;
        accountId = null;
        bookingId = null;
        roomId = null;
        userId = null;
    }

    @AfterClass
    public static void closeTest() throws Exception {
        accountService = null;
        bookingService = null;
        roomService = null;
        userService = null;
    }

    private void persistEntities() throws Exception {
        userService.save(user);
        bookingService.save(booking);
    }
}
