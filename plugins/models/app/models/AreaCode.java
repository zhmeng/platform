package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhangmeng on 16-8-11.
 */
@Entity
@Table(name="area_code")
public class AreaCode extends Model{
    @Id
    private String areacode;
    private String areaname;

    public static final Finder<Integer, AreaCode> finder = new Finder<Integer, AreaCode>(Integer.class, AreaCode.class);

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }
}
