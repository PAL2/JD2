package com.hotel.entity;

import javax.persistence.*;

/**
 * Created by Алексей on 16.10.2016.
 */
@Entity
@Table(name = "account", schema = "booking")
public class Account extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", nullable = false, unique = true)
    private Integer accountId;

    @Basic
    @Column(name = "summa", nullable = false)
    private int summa;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "accountEntity", cascade = CascadeType.ALL)
    private BookingEntity bookingEntity;

    public Account() {
    }

    public Account(int summa) {
        this.summa = summa;
    }

    public Account(int summa, BookingEntity bookingEntity) {
        this.summa = summa;
        this.bookingEntity = bookingEntity;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public BookingEntity getBookingEntity() {
        return bookingEntity;
    }

    public void setBookingEntity(BookingEntity bookingEntity) {
        this.bookingEntity = bookingEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account that = (Account) o;

        if (accountId != that.accountId) return false;
        if (summa != that.summa) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = accountId;
        result = 31 * result + summa;
        return result;
    }
}
