import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModel;
import org.junit.BeforeClass;
import org.junit.Test;
import play.libs.WS;
import play.test.Helpers;
import plugins.spring.Spring;
import services.backend.BankService;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/6/23.
 */
public class Freemarker {
    @BeforeClass
    public static void before(){
        Helpers.start(Helpers.fakeApplication());
    }

    @Test
    public void testOne() throws Exception{
        TemplateModel templateModel = new BeansWrapper().getStaticModels().get("Routes");
        System.out.println(templateModel);
    }
    @Test
    public void getBean() {
        BankService bs = Spring.getBeanOfType(BankService.class);
    }

    @Test
    public void testWS(){
        WS.Response response = WS.url("http://localhost:9000/core").get().get(3, TimeUnit.SECONDS);
        String body = response.getBody();
        System.out.println(body);
    }
}
