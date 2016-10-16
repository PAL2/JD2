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
    private Collection<BookingEntity> bookingsByAccountId;

    @Id
    @Column(name = "account_id", nullable = false)
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

    @OneToMany(mappedBy = "accountByAccountId")
    public Collection<BookingEntity> getBookingsByAccountId() {
        return bookingsByAccountId;
    }

    public void setBookingsByAccountId(Collection<BookingEntity> bookingsByAccountId) {
        this.bookingsByAccountId = bookingsByAccountId;
    }
}
