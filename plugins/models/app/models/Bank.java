package models;

import com.google.common.collect.Lists;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
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

    public static Bank apply(String no, String name, String areacode){
        Bank bank = new Bank();
        bank.no = no;
        bank.name = name;
        bank.areacode = areacode;
        return bank;
    }

    public static List<Bank> getList(){
        List<Bank> banks = Lists.newArrayList();
        banks.add(apply("001", "中国人民银行", "AAA"));
        banks.add(apply("002", "中国银行", "AAB"));
        banks.add(apply("003", "北京银行", "AAC"));
        banks.add(apply("004", "上海银行", "AAD"));
        banks.add(apply("005", "建设银行", "AAE"));
        banks.add(apply("006", "农业银行", "AAF"));
        banks.add(apply("007", "工商银行", "AAG"));
        banks.add(apply("008", "广发银行", "BBA"));
        banks.add(apply("009", "浦发银行", "BBB"));
        banks.add(apply("010", "深圳银行", "BBC"));
        banks.add(apply("011", "东莞银行", "BBD"));
        banks.add(apply("012", "长沙银行", "BBE"));
        return banks;
    }
}
