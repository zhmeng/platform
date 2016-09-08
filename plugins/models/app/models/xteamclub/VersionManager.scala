package models.xteamclub

import java.util.Date
import javax.persistence.{Table, Version, Id, Entity}

import play.db.ebean.Model

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-9-8.
 */
@Entity
@Table(name="teamclub_version_manager")
class VersionManager extends Model{
  @BeanProperty
  @Id
  var id: Integer = _

  @BeanProperty
  var commitVersion: String = _

  @BeanProperty
  var filePath: String = _

  @BeanProperty
  @Version
  var updateVersion:Date = _
}
