import com.avaje.ebean.Ebean;
import com.avaje.ebean.Page;
import controllers.backend.IndexAction;
import forms.BankForm;
import models.Bank;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Configuration;
import play.Play;
import play.libs.Json;
import play.test.FakeApplication;
import play.test.Helpers;
import plugins.ebean.Paging;
import plugins.spring.Spring;
import services.backend.BankService;
import services.backend.BaseBean;

import java.util.List;

/**
 * Created by zhangmeng on 16-6-27.
 */
public class BaseTest {
    private static FakeApplication fakeApplication = null;
    @BeforeClass
    public static void before(){
        fakeApplication = Helpers.fakeApplication();
        Helpers.start(fakeApplication);
    }
    @Test
    public void testSpringFactory(){
        BaseBean bb = (BaseBean)Spring.getBean("selectfactory");
        bb.show();
    }

    @Test
    public void testConf() {
        Configuration config = Play.application().configuration().getConfig("settlement.fudian");
        String remoteurl = config.getString("remoteurl");
        System.out.println(remoteurl);
    }

    @Test
    public void testAspect(){
//        BankService beanOfType = Spring.getBeanOfType(BankService.class);
//        beanOfType.showBank();
//        IndexAction indexAction = Spring.getBeanOfType(IndexAction.class);
//        indexAction.showHello();
//        LogIntercept beanOfType = Spring.getBeanOfType(LogIntercept.class);
//        beanOfType.log();
    }

    @Test
    public void initData(){
        BankForm bankForm = new BankForm();
        bankForm.setDraw(1);
        bankForm.setLength(10);
        bankForm.setStart(0);
        BankService bankService = Spring.getBeanOfType(BankService.class);
        Page<Bank> list = bankService.list(bankForm);
        System.out.println(Paging.toPage(list).toJson());
//        System.out.println(Json.toJson(list));
    }

    @AfterClass
    public static void after(){
        if(fakeApplication != null) Helpers.stop(fakeApplication);
    }
}
