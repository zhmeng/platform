package controllers.backend;

import org.springframework.stereotype.Service;
import play.libs.F;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Result;

import static plugins.freemarker.Freemarker._;
import static plugins.freemarker.Freemarker.view;

/**
 * Created by zhangmeng on 16-6-27.
 */
@Service
public class IndexAction extends Controller {

    public Result login() {
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
}
