package controllers.backend

import commons.Eithers
import play.api.Play
import play.api.mvc.{Action, Controller}
import play.core.j.JavaParsers
import play.libs.Scala
import plugins.freemarker.Freemarker._
import plugins.spring.Spring
import services.backend.ILogin

/**
 * Created by zhangmeng on 16-9-7.
 */
object IndexAction extends Controller{
  val loginSer = Play.current.configuration.getString("loginService")

  val loginService = Spring.getBean(loginSer.getOrElse("")).asInstanceOf[ILogin]

  def login = Action {
    val html = loginService.loginHtml()
    Ok(view(html))
  }

  def loginInvoke = Action(parse.json){ request =>
    val json = request.body
    val username = (json \ "username").as[String]
    val map = Map("username" -> username)
    val resp = loginService.loginInvoke(Scala.asJava(map))
    Ok()
  }

  def index = Action{
    Ok
  }
}
