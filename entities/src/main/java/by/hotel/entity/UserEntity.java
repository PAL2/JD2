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
@Table(name = "user", schema = "booking")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Basic
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Basic
    @Column(name = "user_role", nullable = false, length = 50)
    private String userRole;

    @Basic
    @Column(name = "login", nullable = false, length = 20)
    private String login;

    @Basic
    @Column(name = "password", nullable = false, length = 355)
    private String password;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "userEntity")
    private Set<BookingEntity> bookingEntities = new HashSet<BookingEntity>();

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
                      Set<BookingEntity> bookingEntities) {
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


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

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

    public Set<BookingEntity> getBookingsEntities() {
        return bookingEntities;
    }

    public void setBookingsEntities(Set<BookingEntity> bookingEntities) {
        this.bookingEntities = bookingEntities;
    }
}