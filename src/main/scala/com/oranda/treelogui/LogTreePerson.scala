package com.oranda.treelogui

import java.util.UUID
import scalaz.Show

case class PersonKey(uuid: UUID = UUID.randomUUID())
case class Person(key: PersonKey, name: String)

/**
 * An example of a [[com.oranda.treelogui.LogTreeItem LogTreeItem]] with annotations.
 * It greets two people. Each message is annotated with the UUID of the person greeted.
 */
object LogTreePerson extends LogTreeItem[PersonKey, List[String]] {
  // The '~~' operator annotates the node on the left with the object on the right
  private def greet(person: Person) =
    s"Hello, ${person.name}" ~> s"Said hello to ${person.name}" ~~ person.key

  private def peopleToGreet() = Person(PersonKey(), "Lance") :: Person(PersonKey(), "Channing") :: Nil

  protected def annotationShow = new Show[PersonKey] {
    override def shows(k: PersonKey) = k.uuid.toString
  }

  import scalaz.Scalaz._
  override def result: DescribedComputation[List[String]] =
    peopleToGreet() ~>* ("Greeting everybody", greet)
}
