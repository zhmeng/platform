// @SOURCE:F:/Project/platform/plugins/core/conf/core.routes
// @HASH:fc05a23a3fac4d0e2ed19ebddfa9031df340a551
// @DATE:Thu Jun 23 00:04:51 CST 2016
package core

import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:1
private[this] lazy val controllers_core_BankAction_bankShow0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:2
private[this] lazy val controllers_core_BankAction_testModel1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("testModel"))))
        
def documentation = List(("""GET""", prefix,"""@controllers.core.BankAction@.bankShow()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """testModel""","""@controllers.core.BankAction@.testModel()""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:1
case controllers_core_BankAction_bankShow0(params) => {
   call { 
        invokeHandler(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.core.BankAction]).bankShow(), HandlerDef(this, "controllers.core.BankAction", "bankShow", Nil,"GET", """""", Routes.prefix + """"""))
   }
}
        

// @LINE:2
case controllers_core_BankAction_testModel1(params) => {
   call { 
        invokeHandler(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.core.BankAction]).testModel(), HandlerDef(this, "controllers.core.BankAction", "testModel", Nil,"GET", """""", Routes.prefix + """testModel"""))
   }
}
        
}

}
     