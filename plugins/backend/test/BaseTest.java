import com.avaje.ebean.Ebean;
import com.avaje.ebean.Page;
import com.avaje.ebean.Query;
import com.avaje.ebean.Transaction;
import forms.BankForm;
import models.Bank;
import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Configuration;
import play.Logger;
import play.Play;
import play.libs.F;
import play.libs.WS;
import play.test.FakeApplication;
import play.test.Helpers;
import plugins.ebean.Paging;
import plugins.spring.Spring;
import services.backend.BankService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void testPrepay(){
        final String url = "https://api.zwxpay.com/pay/jspay?showwxpaytitle=1&prepay_id=b499ada8e57326db3ed348510adf3599";
        F.Promise<String> promise = WS.url("https://api.zwxpay.com/pay/jspay").setQueryParameter("showwxpaytitle", "1").setQueryParameter("prepay_id", "b499ada8e57326db3ed348510adf3599").get().map(new F.Function<WS.Response, String>() {
            @Override
            public String apply(WS.Response response) throws Throwable {
                String body = response.getBody();
                Pattern pattern = Pattern.compile("document.location='(.*?)'");
                Matcher matcher = pattern.matcher(body);
                String tecentUrl = "";
                while(matcher.find()){
                    if(StringUtils.isBlank(tecentUrl)){
                        tecentUrl = matcher.group(1);
                    }else{
                        break;
                    }
                }
                Logger.info(tecentUrl);
                if(StringUtils.isNotBlank(tecentUrl)){
                    WS.url(tecentUrl).setHeader("Referer", url).get().map(new F.Function<WS.Response, String>(){
                        @Override
                        public String apply(WS.Response response) throws Throwable {
                            String body = response.getBody();
                            Logger.info(body);
                            return null;
                        }
                    });
                }
                return null;
            }
        });
//        WS.url("https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx20160724144453f42a99fd2f0330742351&package=2587865989").setHeader("Referer", "https://api.zwxpay.com/pay/jspay?showwxpaytitle=1&prepay_id=b499ada8e57326db3ed348510adf3599").get().map(new F.Function<WS.Response, String>(){
//            @Override
//            public String apply(WS.Response response) throws Throwable {
//                String body = response.getBody();
//                Logger.info(body);
//                return null;
//            }
//        });
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch(Exception e){
            e.printStackTrace();
        }
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
