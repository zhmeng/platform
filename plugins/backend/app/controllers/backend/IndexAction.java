package controllers.backend;

import baser.aspect.LogTimeInterceptor;
import baser.aspect.With;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import forms.BankForm;
import models.Bank;
import org.springframework.stereotype.Service;
import play.Logger;
import play.data.Form;
import play.libs.F;
import play.libs.Json;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Result;

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

    public AtomicInteger ai = new AtomicInteger(0);

    public void showHello(){
        Logger.info("show hello.");
    }

    public Result login() {
        Logger.info("login index..");
        return ok(view("backend/login.ftl"));
    }

    public Result index(){
        return ok(view("backend/index.ftl", _("title", "PLATFORM")));
    }

    public F.Promise<Result> demoD() {
        F.Promise<WS.Response> responsePromise = WS.url("https://datatables.net/examples/ajax/data/arrays.txt").get();
        return responsePromise.map(new F.Function<WS.Response, Result>() {
            @Override
            public Result apply(WS.Response response) throws Throwable {
                return ok(response.asJson());
            }
        });
    }

    @With(LogTimeInterceptor.class)
    public Result xnotify(){
        Logger.info("{} address, {}", request().remoteAddress(),  ai.addAndGet(1));
        return TODO;
    }

    public Result demoData(){
        BankForm bankForm = Form.form(BankForm.class).bindFromRequest().get();
        List<Bank> list = Bank.getList();
        List<Bank> banks = list.subList(bankForm.getStart(), bankForm.getEnd() > list.size() ? list.size() : bankForm.getEnd());
        Map<String, Object> map = Maps.newHashMap();
        map.put("draw", bankForm.getDraw());
        map.put("recordsTotal", list.size());
        map.put("recordsFiltered", list.size());
        map.put("data", banks);
        return ok(Json.toJson(map));
    }
}
