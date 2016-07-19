package controllers.backend;

import baser.aspect.LogTimeInterceptor;
import baser.aspect.With;
import com.avaje.ebean.Page;
import com.google.common.collect.Maps;
import forms.BankForm;
import models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.Logger;
import play.data.Form;
import play.libs.F;
import play.libs.Json;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Result;
import plugins.ebean.Paging;
import services.backend.BankService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static plugins.freemarker.Freemarker._;
import static plugins.freemarker.Freemarker.view;

/**
 * Created by zhangmeng on 16-6-27.
 */
@Service
public class IndexAction extends Controller {

    @Autowired
    private BankService bankService;

    public AtomicInteger ai = new AtomicInteger(0);

    public Result login() {
        Logger.info("login index..");
        return ok(view("backend/login.ftl"));
    }

    public Result index(){
        return ok(view("backend/index.ftl", _("title", "PLATFORM")));
    }

    public Result demoData(){
        BankForm bankForm = Form.form(BankForm.class).bindFromRequest().get();
        Page<Bank> banks = bankService.list(bankForm);
        return ok(Paging.toPage(banks).toJson());
    }
}
