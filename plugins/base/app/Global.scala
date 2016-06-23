import play.api.{Application, GlobalSettings}
import plugins.spring.{SpringPlugin, Spring}

/**
 * Created by zhangmeng on 16-6-23.
 */
object Global extends GlobalSettings {

  private var springPlugin:SpringPlugin = _

  override def onStart(app: Application): Unit = {
    springPlugin = new SpringPlugin(app)
    springPlugin.onStart()
  }

  override def onStop(app: Application): Unit = {
    springPlugin.onStop();
  }

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    Spring.getBeanOfType(controllerClass)
  }
}
