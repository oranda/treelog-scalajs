package com.oranda.treelogui

import chandu0101.scalajs.react.components.TreeItem
import org.scalatest._
import scalaz._
import treelog._


class LogTreeItemWithoutAnnotationsSpec extends FlatSpec {

  object LogTreeSimpleSum extends LogTreeItemWithoutAnnotations[Int] {
    override def result: DescribedComputation[Int] = simpleSum

    def simpleSum: DescribedComputation[Int] =
      "Calculating sum" ~< {
        for {
          x ← 11 ~> ("x = " + _)
          y ← 2 ~> ("y = " + _)
          sum ← (x + y) ~> ("Sum is " + _)
        } yield sum
      }
  }

  "A LogTreeItem without annotations" should "produce the expected TreeItem when treeItem is called" in {
    val expectedTreeItem =
      TreeItem("Calculating sum",
        TreeItem("x = 11",
        TreeItem("y = 2",
        TreeItem("Sum is 13"))))
    assert(treesAreEqual(LogTreeSimpleSum.treeItem, expectedTreeItem))
  }
}


