package controllers.minishop

import play.api.mvc.{Action, Controller}

/**
 * Created by zhangmeng on 16-6-29.
 */
object MiniAction extends Controller {
  def index = Action {
    Ok("HELLO")
  }
}
