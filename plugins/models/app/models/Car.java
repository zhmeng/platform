package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhangmeng on 16-8-11.
 */
@Entity
public class Car extends Model{
    @Id
    private Integer id;

    private String name;

    public static final Finder<Integer, Car> finder = new Finder<Integer, Car>(Integer.class, Car.class);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
