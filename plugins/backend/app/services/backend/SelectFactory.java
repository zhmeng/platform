package services.backend;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import plugins.spring.Spring;

/**
 * Created by zhangmeng on 16-7-6.
 */
@Component("selectfactory")
public class SelectFactory implements FactoryBean<BaseBean> {
    @Override
    public BaseBean getObject() throws Exception {
        return (BaseBean)Spring.getBean("beantwo");
    }

    @Override
    public Class<?> getObjectType() {
        return BaseBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
