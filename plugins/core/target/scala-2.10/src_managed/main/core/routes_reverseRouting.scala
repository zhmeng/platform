// @SOURCE:F:/Project/platform/plugins/core/conf/core.routes
// @HASH:fc05a23a3fac4d0e2ed19ebddfa9031df340a551
// @DATE:Thu Jun 23 00:04:51 CST 2016

import core.Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:2
// @LINE:1
package controllers.core {

// @LINE:2
// @LINE:1
class ReverseBankAction {
    

// @LINE:1
def bankShow(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:2
def testModel(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "testModel")
}
                                                
    
}
                          
}
                  


// @LINE:2
// @LINE:1
package controllers.core.javascript {

// @LINE:2
// @LINE:1
class ReverseBankAction {
    

// @LINE:1
def bankShow : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.core.BankAction.bankShow",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:2
def testModel : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.core.BankAction.testModel",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "testModel"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:2
// @LINE:1
package controllers.core.ref {


// @LINE:2
// @LINE:1
class ReverseBankAction {
    

// @LINE:1
def bankShow(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.core.BankAction]).bankShow(), HandlerDef(this, "controllers.core.BankAction", "bankShow", Seq(), "GET", """""", _prefix + """""")
)
                      

// @LINE:2
def testModel(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.core.BankAction]).testModel(), HandlerDef(this, "controllers.core.BankAction", "testModel", Seq(), "GET", """""", _prefix + """testModel""")
)
                      
    
}
                          
}
        
    