package controllers.backend;

import com.avaje.ebean.Ebean;
import models.AppFuncTree;
import org.springframework.stereotype.Service;
import play.Logger;
import play.Play;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static plugins.freemarker.Freemarker._;
import static plugins.freemarker.Freemarker.view;

/**
 * Created by zhangmeng on 16-6-27.
 */
@Service
public class IndexAction extends Controller {

    String appid = Play.application().configuration().getString("appid");

    public AtomicInteger ai = new AtomicInteger(0);

    public Result login() {
        Logger.info("login index..");
        return ok(view("backend/login.ftl"));
    }

    public Result index(){
        List<AppFuncTree> appFuncTrees = Ebean.find(AppFuncTree.class).where().eq("appId", appid).findList();
        return ok(view("backend/index.ftl", _("title", "PLATFORM"), _("trees", appFuncTrees)));
    }


}
