package controllers.core;

import models.Bank;
import org.springframework.stereotype.Service;
import play.Logger;
import play.Play;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Administrator on 2016/6/22.
 */
@Service
public class BankAction extends Controller{
    public Result bankShow(){
        String haha = Play.application().configuration().getString("hahha");
        String gogo = Play.application().configuration().getString("gogo");
        String asv = Play.application().configuration().getString("xxxx");
        String model = Play.application().configuration().getString("model");
        Logger.info("it's bank action...");
        return ok(haha + gogo + asv + model);
    }
    public Result testModel(){
        Bank bank = new Bank("广大银行", "123456");
        bank.save();
        Bank unique = Bank.finder.findList().get(0);
        return ok(unique.getName() + "\t" + unique.getBankNo());
    }
}
