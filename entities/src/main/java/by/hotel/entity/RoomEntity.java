package by.hotel.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Алексей on 16.10.2016.
 */
@Entity
@Table(name = "room", schema = "booking")
public class RoomEntity {
    private Integer roomId;
    private String category;
    private int place;
    private int price;
    private Set<BookingEntity> bookingEntities;

    public RoomEntity() {
    }

    public RoomEntity(String category, int place, int price) {
        this.category = category;
        this.place = place;
        this.price = price;
    }

    public RoomEntity(String category, int place, int price, Set<BookingEntity> bookingEntities) {
        this.category = category;
        this.place = place;
        this.price = price;
        this.bookingEntities = bookingEntities;
    }

    @Id
    @Column(name = "room_id", nullable = false, unique = true)
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roomEntity")
    public Set<BookingEntity> getBookingEntities() {
        return bookingEntities;
    }

    public void setBookingEntities(Set<BookingEntity> bookingEntities) {
        this.bookingEntities = bookingEntities;
    }
}