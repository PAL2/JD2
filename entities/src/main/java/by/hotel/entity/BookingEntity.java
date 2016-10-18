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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id", nullable = false, unique = true)
    private Integer bookingId;

    @Basic
    @Column(name = "start_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date startDate;

    @Basic
    @Column(name = "end_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date endDate;

    @Basic
    @Column(name = "place", nullable = false)
    private int place;

    @Basic
    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Basic
    @Column(name = "b_room_id", nullable = true, insertable = false, updatable = false)
    private Integer roomId;

    @Basic
    @Column(name = "b_user_id", nullable = false, insertable = false, updatable = false)
    private int userId;

    @Basic
    @Column(name = "b_account_id", nullable = true, insertable = false, updatable = false)
    private Integer accountId;

    @Basic
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @ManyToOne
    @JoinColumn(name = "b_room_id", referencedColumnName = "room_id")
    private RoomEntity roomEntity;

    @ManyToOne
    @JoinColumn(name = "b_user_id", nullable = true, referencedColumnName = "user_id")
    private UserEntity userEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_account_id", referencedColumnName = "account_id")
    private AccountEntity accountEntity;

    public BookingEntity() {
    }

    public BookingEntity(Date startDate, Date endDate, int place, String category, Integer roomId, int userId,
                         Integer accountId, String status, RoomEntity roomEntity,
                         UserEntity userEntity, AccountEntity accountEntity) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.category = category;
        this.roomId = roomId;
        this.userId = userId;
        this.accountId = accountId;
        this.status = status;
        this.roomEntity = roomEntity;
        this.userEntity = userEntity;
        this.accountEntity = accountEntity;
    }


    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RoomEntity getRoomEntity() {
        return roomEntity;
    }

    public void setRoomEntity(RoomEntity roomEntity) {
        this.roomEntity = roomEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
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
}