package by.hotel.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Collection;

/**
 * Created by Алексей on 16.10.2016.
 */
@Entity
@Table(name = "room", schema = "booking", catalog = "")
public class RoomEntity {
    private int roomId;
    private String category;
    private int place;
    private int price;
    private Collection<BookingEntity> bookingsByRoomId;

    @Id
    @Column(name = "room_id", nullable = false)
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
    @Column(name = "place", nullable = false)
    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (roomId != that.roomId) return false;
        if (place != that.place) return false;
        if (price != that.price) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomId;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + place;
        result = 31 * result + price;
        return result;
    }

    @OneToMany(mappedBy = "roomByRoomId")
    public Collection<BookingEntity> getBookingsByRoomId() {
        return bookingsByRoomId;
    }

    public void setBookingsByRoomId(Collection<BookingEntity> bookingsByRoomId) {
        this.bookingsByRoomId = bookingsByRoomId;
    }
}
