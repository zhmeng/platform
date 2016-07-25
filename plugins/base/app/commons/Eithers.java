package commons;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.Form;
import play.libs.F;
import play.libs.Json;
import play.mvc.Http;

/**
 * Created by zhangmeng on 16-7-25.
 */
public class Eithers {

    public static <T> JsonNode failure(Form<T> form){
        ObjectNode onode = Json.newObject();
        onode.put("status",ErrorCodes.BAD_REQUEST._1);
        onode.put("message", ErrorCodes.BAD_REQUEST._2);
        onode.put("errors", form.errorsAsJson());
        return shadow(onode);
    }

    public static <T> JsonNode failure(ErrorCode ecode){
        ObjectNode onode = Json.newObject();
        onode.put("status", ecode._1);
        if( ecode._2 != null) onode.put("message", ecode._2);
        if(ecode.errors != null)onode.put("errors", ecode.errors);
        return shadow(onode);
    }

    public static <T> JsonNode success(){
        ObjectNode onode = Json.newObject();
        onode.put("status",ErrorCodes.SUCCESS._1);
        onode.put("message", ErrorCodes.SUCCESS._2);
        return shadow(onode);
    }

    public static <T> JsonNode toJson(F.Either<T, ErrorCode> code){
        if(code.left.isDefined()){
            T l = code.left.get();
            if(l instanceof Boolean){
                if(((Boolean) l) == true){
                    return success();
                }else{
                    return failure(ErrorCodes.BUSIZ_FAILURE);
                }
            }else{
                return shadow(Json.toJson(code.left.get()));
            }
        }else{
            return failure(code.right.get());
        }
    }

    public static JsonNode shadow(JsonNode node){
        if(Http.Context.current.get() != null){
            Http.Context.current().args.put("RESPONSE_JSON", node);
        }
        return node;
    }
}