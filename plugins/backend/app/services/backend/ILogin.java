package services.backend;

import com.fasterxml.jackson.databind.JsonNode;
import commons.ErrorCode;
import play.api.libs.json.JsValue;
import play.libs.F;

import java.util.Map;

/**
 * Created by zhangmeng on 16-9-7.
 */
public interface ILogin {
    String loginHtml();
    F.Either<String, ErrorCode> loginInvoke(Map<String, String> req);
}
