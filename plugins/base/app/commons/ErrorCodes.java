package commons;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhangmeng on 16-7-25.
 */
public class ErrorCodes {

    //系统级别
    public static ErrorCode SUCCESS = new ErrorCode(0, "SUCCESS");
    public static ErrorCode BAD_REQUEST = new ErrorCode(400, "参数验证失败");
    public static ErrorCode BUSIZ_FAILURE = new ErrorCode(404, "业务失败");
    public static ErrorCode SYSTEM_ERROR = new ErrorCode(500, "系统异常");


    public static ErrorCode with(Exception e){
        if(StringUtils.isNotBlank(e.getMessage())){
            return new ErrorCode(10000, e.getMessage());
        }else {
            return SYSTEM_ERROR;
        }
    }

}