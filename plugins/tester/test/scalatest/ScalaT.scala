package scalatest

/**
 * Created by zhangmeng on 16-8-10.
 */
class ScalaT {
  private var a: Int = 10
  def this(x: Int){
    this()
    a = 20
  }
}
class Person(var name: String, var age: Int)

class Pizza {
  var curstSize = 12
  var curstType = "Thin"

  def this(x: Int){
    this()
    this.curstSize = 13
  }
}
object ScalaTT extends App {
  val a1 = new Person("Al", 12)

  val scalaT = new ScalaT()
  val pizza = new Pizza

  val x = null
  println(x)

  def show(i: Int): Unit ={
    if(i == 10) {
      i
    }
    println(i)
  }

  def testEx = {
    try{
      throw new Exception("Hello")
    } catch {
      case e: Exception =>
        e.getMessage
    }
    "world"
  }
  println(testEx)
}
