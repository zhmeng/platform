package forms;

import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * Created by zhangmeng on 16-7-18.
 */
public class BankForm extends PageForm {

    private Integer id;
    private String no;
    private String name;
    private String areacode;
    private String address;

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
}
