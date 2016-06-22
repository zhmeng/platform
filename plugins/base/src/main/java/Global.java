import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.mvc.Action;
import play.mvc.Http;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/6/22.
 */
public class Global extends GlobalSettings{

    protected ApplicationContext ctx;

    @Override
    public void onStart(Application application) {
        String springConfigurationName = "components.xml";
        ctx = new ClassPathXmlApplicationContext(springConfigurationName);
        Logger.debug("Loading Spring configuration {}", springConfigurationName);
    }

    @Override
    public <A> A getControllerInstance(Class<A> aClass) throws Exception {
        return ctx.getBean(aClass);
    }

    @Override
    public Action onRequest(Http.Request request, Method method) {
        Logger.info("request ... " + method.getName());
        return super.onRequest(request, method);
    }

    @Override
    public void onStop(Application application) {
        Logger.info("Application has shutdown");
    }
}
