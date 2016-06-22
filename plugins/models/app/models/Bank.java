package models;

import play.db.ebean.Model;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/6/22.
 */
@Entity
public class Bank extends Model{
    private String name;
    private String bankNo;

    public Bank(String name, String bankNo){
        this.name = name;
        this.bankNo = bankNo;
    }

    public static Finder<Integer, Bank> finder = new Finder<Integer, Bank>(Integer.class, Bank.class);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
}
