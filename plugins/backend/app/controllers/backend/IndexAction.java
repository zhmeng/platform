package controllers.backend;

import org.springframework.stereotype.Service;
import play.mvc.Controller;
import play.mvc.Result;

import static plugins.freemarker.Freemarker._;
import static plugins.freemarker.Freemarker.view;

/**
 * Created by zhangmeng on 16-6-27.
 */
@Service
public class IndexAction extends Controller {
    public Result index(){
        return ok(view("backend/index.ftl", _("title", "PLATFORM")));
    }
}
