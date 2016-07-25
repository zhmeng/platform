package commons;

import org.apache.commons.lang3.StringUtils;
import play.data.Form;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmeng on 16-7-25.
 */
public class Validations {
    public static <T> void required(Form<T> form,String... keys){
        for(String key : keys){
            String value = form.data().get(key);
            if(StringUtils.isEmpty(StringUtils.trim(value))){
                List<ValidationError> error = new ArrayList<ValidationError>(2);
                error.add(new ValidationError(key,"error.required"));
                form.errors().put(key,error);
            }
        }
    }

    public static <T> void filter(Form<T> form,String... keys) {
        for(String key : keys){
            form.data().remove(key);
        }
    }

}
