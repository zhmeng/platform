package plugin.teamclub

import router.RoutePlugin

/**
 * Created by zhangmeng on 16-6-30.
 */
class TeamClubPlugin(app: play.api.Application) extends RoutePlugin {
  def prefix = Some("teamclub")
}
