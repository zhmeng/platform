package controllers.backend;

import org.springframework.stereotype.Service;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.concurrent.atomic.AtomicInteger;

import static plugins.freemarker.Freemarker._;
import static plugins.freemarker.Freemarker.view;

/**
 * Created by zhangmeng on 16-6-27.
 */
@Service
public class IndexAction extends Controller {

    public AtomicInteger ai = new AtomicInteger(0);

    public Result login() {
        Logger.info("login index..");
        return ok(view("backend/login.ftl"));
    }

    public Result index(){
        return ok(view("backend/index.ftl", _("title", "PLATFORM")));
    }


}
