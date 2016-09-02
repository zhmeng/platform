package commons

/**
 * Created by zhangmeng on 16-9-1.
 */
case class ErrorCode(status: Int, message: String)

object ErrorCodes {
  val SUCCESS = ErrorCode(0, "SUCCESS")
}
