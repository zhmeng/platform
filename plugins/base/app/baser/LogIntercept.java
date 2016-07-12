package baser;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import play.Logger;

import java.util.Objects;

/**
 * Created by zhangmeng on 16-7-12.
 */
@Aspect
@Component
public class LogIntercept {
    public void log(){
        Logger.info("log it.");
    }
    @Pointcut(value="execution(public * controllers.backend..*.*(..))")
    public void writeLog(){
    }
//
    @Before("writeLog()")
    public void before(){
        this.printLog("@Before 方法执行前---做日志");
    }
//
//    @Around("writeLog()")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        this.printLog("@Around 方法执行前---做日志");
//        Object o = pjp.proceed();
//        this.printLog("@Around 方法执行后---做日志");
//        return o;
//    }
//
//    @After("writeLog()")
//    public void after(){
//        this.printLog("@After 方法执行后---做日志");
//    }
//
    private void printLog(String str) {
        Logger.info(str);
    }
}

