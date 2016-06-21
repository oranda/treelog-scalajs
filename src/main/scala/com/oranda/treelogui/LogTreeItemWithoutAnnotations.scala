package com.oranda.treelogui

import scalaz.Show

/**
 * A [[com.oranda.treelogui.LogTreeItem LogTreeItem]] without annotations.
 *
 * Classes that extend this trait only need to override `result` to form
 * the `DescribedComputation`.
 */
trait LogTreeItemWithoutAnnotations[Result] extends LogTreeItem[Nothing, Result] {
  override def annotationShow = new Show[Nothing] {
    override def shows(n: Nothing): String = ""
  }
}