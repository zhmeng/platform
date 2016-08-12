package plugin.backend

import play.api.Logger
import play.core.Router
import router.{RoutePlugin}

/**
 * Created by zhangmeng on 16-6-30.
 */
class BackendPlugin(app: play.api.Application) extends RoutePlugin {
  def prefix = Some("backend")
}
