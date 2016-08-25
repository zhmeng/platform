package scalatest

import java.util

import org.apache.bcel.classfile.InnerClass
import play.libs.Scala

import scala.util.{Failure, Success, Try}

/**
 * Created by zhangmeng on 16-8-10.
 */
object TTT {
  def exprToInt(expr: String): Int => Boolean = {
    val Array(mark, num, _*) = expr.split(" ")
    val numInt = num.toInt
    mark match {
      case "<" => numInt.>
      case ">" => numInt.<
      case ">=" => numInt.<=
      case "<=" => numInt.>=
    }
  }
  case class RangeMatcher(range: Seq[String]) {
    val rangeFunc: Seq[(Int) => Boolean] = range.map(exprToInt)
    def check(input: Int) = rangeFunc.forall(_(input))
  }

  def main(args: Array[String]): Unit = {
    val requirements = Seq(">= 3", "< 7")
    val rangeMatcher = RangeMatcher(requirements)
    val results = Seq(1, 3, 5, 7, 9) map (rangeMatcher.check)
    println(results.mkString(","))
  }
}

trait User {
  def name: String
}
trait B {
  self =>
  def foo(): Unit = {
    println("asdf")
  }
}

class XX extends Function1[String, String] {
  override def apply(v1: String): String = ???
}

object XMain extends App {
  def foo(i: Int): Int = { i + 2 }
}