package models;

import com.google.common.collect.Lists;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhangmeng on 16-7-15.
 */
@Entity
public class Bank extends Model{
    @Id
    private Integer id;
    private String no;
    private String name;
    private String areacode;
    private String address;

    @OneToOne
    @JoinColumn(name="areacode")
    private AreaCode areaCode;

    @Version
    private Integer updateVersion;

    public static Model.Finder<Integer, Bank> finder = new Model.Finder<Integer, Bank>(Integer.class, Bank.class);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUpdateVersion() {
        return updateVersion;
    }

    public void setUpdateVersion(Integer updateVersion) {
        this.updateVersion = updateVersion;
    }

    public AreaCode getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(AreaCode areaCode) {
        this.areaCode = areaCode;
    }

    public static Bank apply(String no, String name, String areacode){
        Bank bank = new Bank();
        bank.no = no;
        bank.name = name;
        bank.areacode = areacode;
        return bank;
    }
}
