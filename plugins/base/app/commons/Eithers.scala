package commons

import com.fasterxml.jackson.databind.JsonNode
import play.api.libs.json._

/**
 * Created by zhangmeng on 16-9-2.
 */
object Eithers {
  val success = {
    Json.toJson(ErrorCodes.SUCCESS)
  }
}
