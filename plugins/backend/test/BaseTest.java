import baser.LogIntercept;
import controllers.backend.IndexAction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.FakeApplication;
import play.test.Helpers;
import plugins.spring.Spring;
import services.backend.BaseBean;
import services.backend.SelectFactory;

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
    public void testAspect(){
        IndexAction indexAction = Spring.getBeanOfType(IndexAction.class);
        indexAction.showHello();
//        LogIntercept beanOfType = Spring.getBeanOfType(LogIntercept.class);
//        beanOfType.log();
    }

    @AfterClass
    public static void after(){
        if(fakeApplication != null) Helpers.stop(fakeApplication);
    }
}
