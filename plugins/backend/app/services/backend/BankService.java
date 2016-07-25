package services.backend;

import baser.aspect.LogTimeInterceptor;
import baser.aspect.With;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;
import commons.Eithers;
import commons.ErrorCode;
import commons.ErrorCodes;
import forms.BankForm;
import models.Bank;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import play.Logger;
import play.data.Form;
import play.libs.F;

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

    public F.Either<Bank, ErrorCode> modify(BankForm bankForm){
        try{
            if(bankForm.getId() != null){
                Bank bank = Bank.finder.byId(bankForm.getId());
                BeanUtils.copyProperties(bankForm, bank);
                bank.update();
                return F.Either.Left(bank);
            }else{
                Bank bank = new Bank();
                BeanUtils.copyProperties(bankForm, bank);
                bank.update();
                return F.Either.Left(bank);
            }
        }catch(Exception e){
            Logger.error("", e);
            return F.Either.Right(ErrorCodes.with(e));
        }
    }

    public F.Either<Boolean, ErrorCode> delete(Integer id){
        try{
            Bank.finder.byId(id).delete();
            return F.Either.Left(true);
        }catch(Exception e){
            Logger.error("", e);
            return F.Either.Right(ErrorCodes.with(e));
        }
    }
}
