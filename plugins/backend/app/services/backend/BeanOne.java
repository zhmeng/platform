package services.backend;

import org.springframework.stereotype.Service;

/**
 * Created by zhangmeng on 16-7-6.
 */
@Service("beanone")
public class BeanOne implements BaseBean{
    @Override
    public void show() {
        System.out.println("bean one..");
    }
}
