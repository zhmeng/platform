package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zhangmeng on 16-8-11.
 */
@Entity
public class Address extends Model{
    @Id
    private Integer id;
    private String name;

    public static final Finder<Integer, Address> finder = new Finder<Integer, Address>(Integer.class, Address.class);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address(String name){
        this.name = name;
    }
}
