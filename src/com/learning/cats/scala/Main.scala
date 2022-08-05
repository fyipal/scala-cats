package com.learning.cats.scala

import cats.Monoid
import cats.instances.map._
import cats.syntax.monoid._

object Main extends App {

  case class Person(name: String, language: List[String])

  def combineFold[T](list: List[T])(implicit monoid: Monoid[T]): T = list.foldLeft(monoid.empty)(_ |+| _)

  implicit val PersonMonoid: Monoid[Person] = Monoid.instance(
    Person("", List()),
    (p1, p2) => Person(p1.name, p1.language ++ p2.language)
  )

  val persons = List(
    Map("Amresh" -> Person("Amresh", List("A", "B")), "Tabrez" -> Person("Tabrez", List("A", "B"))),
    Map("Amresh" -> Person("Amresh", List("C")))
  )

  println(persons)
  val result = combineFold(persons)
  println(result)

}
