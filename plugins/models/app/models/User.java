package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhangmeng on 16-8-11.
 */
@Entity
public class User extends Model{
    @Id
    private Integer id;

    private String username;

    private String password;


    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static final Finder<Integer, User> finder = new Finder<Integer, User>(Integer.class, User.class);


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
