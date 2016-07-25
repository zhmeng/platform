package controllers.backend;

import com.avaje.ebean.Page;
import commons.Eithers;
import commons.ErrorCode;
import commons.Libs;
import commons.Validations;
import forms.BankForm;
import models.Bank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.data.Form;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import plugins.ebean.Paging;
import services.backend.BankService;

/**
 * Created by zhangmeng on 16-7-25.
 */
@Service
public class BankAction extends Controller{

    @Autowired
    private BankService bankService;

    public Result list(){
        BankForm bankForm = Form.form(BankForm.class).bindFromRequest().get();
        Page<Bank> banks = bankService.list(bankForm);
        return ok(Paging.toPage(banks).toJson());
    }

    public Result modify(){
        Form<BankForm> bankForm = Form.form(BankForm.class).bindFromRequest();
        Validations.required(bankForm, "no", "name");
        if(bankForm.hasErrors()){
            return ok(Eithers.failure(bankForm));
        }
        F.Either<Bank, ErrorCode> modify = bankService.modify(bankForm.get());
        return ok(Eithers.toJson(modify));
    }

    public Result delete(){
        Form<BankForm> bankForm = Form.form(BankForm.class).bindFromRequest();
        Validations.required(bankForm, "id");
        if(bankForm.hasErrors()){
            return ok(Eithers.failure(bankForm));
        }
        Integer id = bankForm.get().getId();
        F.Either<Boolean, ErrorCode> delete = bankService.delete(Libs.toInt(id, -1));
        return ok(Eithers.toJson(delete));
    }
}
