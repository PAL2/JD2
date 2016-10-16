package by.hotel.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Collection;

/**
 * Created by Алексей on 16.10.2016.
 */
@Entity
@Table(name = "account", schema = "booking", catalog = "")
public class AccountEntity {
    private int accountId;
    private int summa;
    private BookingEntity bookingEntity;

    public AccountEntity() {
    }

    public AccountEntity(int accountId, int summa) {
        this.accountId = accountId;
        this.summa = summa;
    }

    public AccountEntity(int accountId, int summa, BookingEntity bookingEntity) {
        this.accountId = accountId;
        this.summa = summa;
        this.bookingEntity = bookingEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", nullable = false, unique = true)
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "summa", nullable = false)
    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        if (accountId != that.accountId) return false;
        if (summa != that.summa) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountId;
        result = 31 * result + summa;
        return result;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "accountByAccountId", cascade = CascadeType.ALL)
    public BookingEntity getBookingEntity() {
        return bookingEntity;
    }

    public void setBookingEntity(BookingEntity bookingEntity) {
        this.bookingEntity = bookingEntity;
    }
}
