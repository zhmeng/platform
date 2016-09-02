package controllers.teamclub

import commons.{Eithers, ErrorCode}
import play.api.Logger
import play.api.mvc._
import plugins.freemarker.Freemarker.view
import play.api.libs.json._

/**
 * Created by zhangmeng on 16-8-30.
 */
object IndexController extends Controller{
  def login = Action { request =>
    Ok(view("teamclub/login.ftl"))
  }
  def loginInvoke = Action {
    Ok(Eithers.success)
  }
}