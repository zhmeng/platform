package services.backend;

import baser.aspect.LogTimeInterceptor;
import baser.aspect.With;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;
import forms.BankForm;
import models.Bank;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangmeng on 16-6-23.
 */
@Service
public class BankService {
    public Page<Bank> list(BankForm bankForm){
        ExpressionList<Bank> expList = Bank.finder.where();
        if(StringUtils.isNotBlank(bankForm.getName())){
            expList.like("name", "%" + bankForm.getName() + "%");
        }
        Page<Bank> page = expList.findPagingList(bankForm.getLength()).getPage(bankForm.currentPage());
        return page;
    }
}
