package com.oranda.treelogui


import chandu0101.scalajs.react.components.TreeItem
import org.scalatest._
import scalaz._
import Scalaz._

import org.scalatest.FlatSpec

class LogTreeItemSpec extends FlatSpec {

  object LogTreeSimpleSum extends LogTreeItem[String, Int] {
    override def result: DescribedComputation[Int] = simpleSum

    protected def annotationShow = new Show[String] {
      override def shows(text: String) = text
    }

    def simpleSum: DescribedComputation[Int] =
      "Calculating sum" ~< {
        for {
          x ← 11 ~> ("x = " + _) ~~ "assignment of x"
          y ← 2 ~> ("y = " + _) ~~ "assignment of y"
          sum ← (x + y) ~> ("Sum is " + _) ~~ "summing x and y"
        } yield sum
      }
  }

  "A LogTreeItem with annotations" should "produce the expected TreeItem when treeItem is called" in {
    val expectedTreeItem =
      TreeItem("Calculating sum",
        TreeItem("x = 11 - [assignment of x]",
          TreeItem("y = 2 - [assignment of y]",
            TreeItem("Sum is 13 - [summing x and y]"))))
    assert(treesAreEqual(LogTreeSimpleSum.treeItem, expectedTreeItem))
  }
}

