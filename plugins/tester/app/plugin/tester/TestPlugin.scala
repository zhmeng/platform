package plugin.tester

import router.RoutePlugin

/**
 * Created by zhangmeng on 16-8-9.
 */
class TestPlugin(app: play.api.Application) extends RoutePlugin {
  def prefix = Some("test")
}