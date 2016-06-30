package router

import java.io.File

import controllers.Assets
import play.api.{Logger, Routes}
import play.api.mvc.{Handler, RequestHeader}
import play.core.Router.{HandlerDef, Param, Route}
import play.core.{DynamicPart, PathPattern, Router, StaticPart}
import play.router.RoutesCompiler.{RoutesCompilationError, RouteFileParser}

import scala.collection.immutable.HashMap
import scala.collection.mutable

/**
 * Created by zhangmeng on 16-6-30.
 */
object DynamicRoutes extends Router.Routes{

  private var _prefix = "/"

  var documentation: Seq[(String, String, String)] = scala.collection.mutable.ArrayBuffer()

  def setPrefix(prefix: String) = {_prefix = prefix}

  def prefix = _prefix

  lazy val defaultPrefix = { if(prefix.endsWith("/")) "" else "/" }

  lazy val controllers_Assets_at = Route("GET", PathPattern(List(StaticPart(prefix),StaticPart(defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))

  lazy val defaultRoutes: PartialFunction[RequestHeader, Handler] = {
    case controllers_Assets_at(params) => {
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(Assets.at(path, file), HandlerDef(this, "controllers.Routes", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", prefix + """assets/$file<.+>"""))
      }
    }
  }

  override def handlerFor(request: RequestHeader): Option[Handler] = {
    routes.lift(request)
  }

  var routes: PartialFunction[RequestHeader, Handler] = defaultRoutes

  def appendRoutes(appendRoutes: PartialFunction[RequestHeader, Handler], document: Seq[(String, String, String)]) = this.synchronized {
    routes = routes orElse appendRoutes
    documentation ++= document
  }

}