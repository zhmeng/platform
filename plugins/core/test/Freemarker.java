import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.Helpers;
import plugins.spring.Spring;
import services.core.BankService;

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
        System.out.println(bs.showBank());
    }
}
