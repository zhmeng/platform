package jira

import javax.persistence.{Entity, MappedSuperclass, Table}

import play.db.ebean.Model

import scala.beans.BeanProperty

/**
 * Created by zhangmeng on 16-8-30.
 */
@Entity
@Table(name="app_user")
class User extends Model{
  @BeanProperty
  var ID:Integer = _
  @BeanProperty
  var userKey: String = _
  @BeanProperty
  var lowerUserName: String = _
}
