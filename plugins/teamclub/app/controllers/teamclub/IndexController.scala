package controllers.teamclub

import com.avaje.ebean.Ebean
import commons.Eithers
import modelx.teamclub.User
import play.api.mvc._
import plugins.freemarker.Freemarker.view

/**
 * Created by zhangmeng on 16-8-30.
 */
object IndexController extends Controller{
  def login = Action { request =>
    Ok(view("teamclub/login.ftl"))
  }
  def loginInvoke = Action {
    val user = Ebean.getServer("jira").find(classOf[User]).where().eq("userKey", "").findUnique()
    if(user == null) {
      Ok(Eithers.success)
    }else {
      Ok(Eithers.failure("未找到用户"))
    }
  }
}