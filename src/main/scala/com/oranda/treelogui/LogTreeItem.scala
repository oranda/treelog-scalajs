package com.oranda.treelogui

import chandu0101.scalajs.react.components.TreeItem
import treelog.{LogTreeLabel, LogTreeSyntax}

import scalaz.Show

/**
 * Provides a way of building a [[chandu0101.scalajs.react.components.TreeItem TreeItem]] from a
 * [[treelog.LogTreeSyntax.DescribedComputation DescribedComputation]]
 * (declared in [[treelog.LogTreeSyntax LogTreeSyntax]]).
 *
 * Classes that extend this trait should override `result` to form the `DescribedComputation`,
 * and `annotationShow` to specify what the annotation for a node should display.
 */
trait LogTreeItem[Annotation, Result] extends LogTreeSyntax[Annotation] {

  /**
   * Forms the `DescribedComputation` used by this `LogTreeItem`.
   */
  protected def result: DescribedComputation[Result]

  /**
   * Specifies what the annotation for a node should display for this `LogTreeItem`.
   */
  protected def annotationShow: Show[Annotation]

  /**
   * Builds the UI `TreeItem` using the `DescribedComputation` and annotation messages specified
   * in the trait implementation.
   */
  final def treeItem: TreeItem = {

    def showSuccess(success: Boolean, s: String) = if (success) s else "Failed: " + s

    def showDescription(label: LogTreeLabel[Annotation]) =
      label.fold(_.description, _ ⇒ "No Description")

    def showAnnotations(
        annotations: Set[Annotation],
        line: String,
        annotationShow: Show[Annotation]) =
      if (annotations.isEmpty) line
      else line + " - [" + annotations.map(annotationShow.show).mkString(", ") + "]"

    def buildTreeItem(t: LogTree): TreeItem = {
      val label = t.rootLabel
      val description: String = showDescription(label)
      val subtrees: Seq[LogTree] = t.subForest
      val reactSubtrees = subtrees.map(buildTreeItem)
      val line = showAnnotations(label.annotations, showSuccess(label.success(), description), annotationShow)

      TreeItem(line, reactSubtrees:_*)
    }

    buildTreeItem(result.run.written)
  }
}