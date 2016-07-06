package services.backend;

import org.springframework.stereotype.Service;

/**
 * Created by zhangmeng on 16-7-6.
 */
@Service("beantwo")
public class BeanTwo implements BaseBean{
    @Override
    public void show() {
        System.out.println("bean two..");
    }
}
