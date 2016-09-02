import play.api.libs.json.{JsPath, Reads, Json, Writes}
import play.api.libs.functional.syntax._
/**
 * Created by zhangmeng on 16-9-1.
 */
package object commons {
  implicit val errorcodeWriters: Writes[ErrorCode] = (
    (JsPath \ "status").write[Int] and
    (JsPath \ "message").write[String]
  )(unlift(ErrorCode.unapply))

  implicit val errorcodeReaders: Reads[ErrorCode] = (
    (JsPath \ "status").read[Int] and
    (JsPath \ "message").read[String]
  )(ErrorCode.apply _)
}
