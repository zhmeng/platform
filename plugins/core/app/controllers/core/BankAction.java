package controllers.core;

import models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.Logger;
import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import services.core.BankService;

/**
 * Created by Administrator on 2016/6/22.
 */
@Service
public class BankAction extends Controller{

    @Autowired
    private BankService bankService;

    public Result bankShow(){
        String haha = Play.application().configuration().getString("hahha");
        String gogo = Play.application().configuration().getString("gogo");
        String asv = Play.application().configuration().getString("xxxx");
        String model = Play.application().configuration().getString("model");
        Logger.info("it's bank action...");
        String showBank = bankService.showBank();
        return ok(showBank);
    }
    public Result testModel(){
        Bank bank = new Bank("广大银行", "123456");
        bank.save();
        Bank unique = Bank.finder.findList().get(0);
        return ok(unique.getName() + "\t" + unique.getBankNo());
    }

    public Result freemarker(){
        return ok("freemarker");
    }

}
