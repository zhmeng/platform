import com.avaje.ebean.*;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

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

    @Test
    public void transactionTest() throws Exception{
        ExecutorService executor = Executors.newFixedThreadPool(10);
        final AtomicInteger ai = new AtomicInteger(0);
        for(int i = 0 ;  i < 10 ; i++){
            LockSupport.parkNanos(300);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Transaction transaction = Ebean.beginTransaction();
                    try {
                        Query<Bank> query = Ebean.createQuery(Bank.class);
                        query.where().eq("id", 104);
                        query.setForUpdate(true);
                        query.findUnique();
                        if(ai.incrementAndGet() == 1){
                            throw new Exception("爆掉了..");
                        }
                        Bank bank = query.findUnique();
                        String address = Thread.currentThread().getName() + System.currentTimeMillis();
                        bank.setAddress(address);
                        System.out.println("设置 104 bank 地址为: " + address);
                        bank.save();
                        transaction.commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        transaction.rollback();
                    } finally {
                        transaction.end();
                    }
                }
            });
        }
        executor.shutdown();
        try{
            while(!executor.awaitTermination(3, TimeUnit.SECONDS)) ;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("finally end.");


    }

    @AfterClass
    public static void after(){
        if(fakeApplication != null) Helpers.stop(fakeApplication);
    }
}
