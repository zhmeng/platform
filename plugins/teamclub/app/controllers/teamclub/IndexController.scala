package controllers.teamclub

import com.avaje.ebean.{Ebean, EbeanServer}
import commons.Eithers
import models.teamclub.AppUser
import play.Logger
import play.api.data.Form
import play.api.mvc._
import plugins.freemarker.Freemarker.view

/**
 * Created by zhangmeng on 16-8-30.
 */
object IndexController extends Controller{
  def login = Action { request =>
    Ok(view("teamclub/login.ftl"))
  }
  def loginInvoke = Action(parse.json) { request =>
    Logger.info("come in")
    (request.body \ "username").asOpt[String].map { name =>
      val user = Ebean.getServer("jira").find(classOf[AppUser]).where.eq("userKey", name).findUnique
      if(user != null) {
        Ok(Eithers.success)
      }else {
        Ok(Eithers.failure("未找到该用户"))
      }
    }.getOrElse {
      Ok(Eithers.failure("登录失败"))
    }
  }
}