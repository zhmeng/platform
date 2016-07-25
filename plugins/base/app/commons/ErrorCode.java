package commons;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.F;

/**
 * Created by zhangmeng on 16-7-25.
 */
public class ErrorCode extends F.Tuple<Integer, String> {
    public final int code;
    public final String message;
    public final JsonNode errors;
    public ErrorCode(Integer code, String message) {
        this(code, message, null);
    }
    public ErrorCode(Integer code, String message,JsonNode errors) {
        super(code, message);
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public ErrorCode with(String msg){
        return new ErrorCode(this.code,this.message + msg);
    }
}
