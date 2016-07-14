package router

import play.api.Logger
import play.core.Router

/**
 * Created by zhangmeng on 16-6-30.
 */
trait RoutePlugin extends play.api.Plugin{
  def prefix: Option[String]
  def companion[T](name : String)(implicit man: Manifest[T]) : T = {
    prefix match {
      case Some(prf) => {
        Class.forName(prf + "." + name + "$").getField("MODULE$").get(man.runtimeClass).asInstanceOf[T]
      }
      case None => {
        Class.forName(name + "$").getField("MODULE$").get(man.runtimeClass).asInstanceOf[T]
      }
    }
  }


  override def onStart(): Unit = {
    val selfRoutes = companion[Router.Routes]("Routes")
    prefix.map{
      prefixStr => DynamicRoutes.appendRoutes(prefixStr, selfRoutes)
    }
  }
}
