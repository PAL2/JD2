package by.hotel.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Collection;

/**
 * Created by Алексей on 16.10.2016.
 */
@Entity
@Table(name = "user", schema = "booking")
public class UserEntity {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String userRole;
    private String login;
    private String password;
    private Collection<BookingEntity> bookingEntities;

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String userRole, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.login = login;
        this.password = password;
    }

    public UserEntity(String firstName, String lastName, String userRole, String login, String password,
                      Collection<BookingEntity> bookingEntities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.login = login;
        this.password = password;
        this.bookingEntities = bookingEntities;
    }

    public UserEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "user_role", nullable = false, length = 50)
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 20)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 355)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (userRole != null ? !userRole.equals(that.userRole) : that.userRole != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    public Collection<BookingEntity> getBookingsEntities() {
        return bookingEntities;
    }

    public void setBookingsEntities(Collection<BookingEntity> bookingEntities) {
        this.bookingEntities = bookingEntities;
    }
}