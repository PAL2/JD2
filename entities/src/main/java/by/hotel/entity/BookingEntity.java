package by.hotel.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by Алексей on 16.10.2016.
 */
@Entity
@Table(name = "booking", schema = "booking")
public class BookingEntity {
    private Integer bookingId;
    private Date startDate;
    private Date endDate;
    private int place;
    private String category;
    private Integer roomId;
    private int userId;
    private Integer accountId;
    private String status;
    private RoomEntity roomByRoomId;
    private UserEntity userByUserId;
    private AccountEntity accountEntity;

    public BookingEntity() {
    }

    public BookingEntity(Date startDate, Date endDate, int place, String category, Integer roomId, int userId,
                         Integer accountId, String status, RoomEntity roomByRoomId,
                         UserEntity userByUserId, AccountEntity accountEntity) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.category = category;
        this.roomId = roomId;
        this.userId = userId;
        this.accountId = accountId;
        this.status = status;
        this.roomByRoomId = roomByRoomId;
        this.userByUserId = userByUserId;
        this.accountEntity = accountEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id", nullable = false, unique = true)
    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Basic
    @Column(name = "start_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "place", nullable = false)
    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Basic
    @Column(name = "category", nullable = false, length = 50)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "room_id", nullable = true)
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "account_id", nullable = true, insertable = false, updatable = false)
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingEntity that = (BookingEntity) o;

        if (bookingId != that.bookingId) return false;
        if (place != that.place) return false;
        if (userId != that.userId) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (roomId != null ? !roomId.equals(that.roomId) : that.roomId != null) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookingId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + place;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (roomId != null ? roomId.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

//    @ManyToOne
//    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
//    public RoomEntity getRoomByRoomId() {
//        return roomByRoomId;
//    }
//
//    public void setRoomByRoomId(RoomEntity roomByRoomId) {
//        this.roomByRoomId = roomByRoomId;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
//    public UserEntity getUserByUserId() {
//        return userByUserId;
//    }
//
//    public void setUserByUserId(UserEntity userByUserId) {
//        this.userByUserId = userByUserId;
//    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
}