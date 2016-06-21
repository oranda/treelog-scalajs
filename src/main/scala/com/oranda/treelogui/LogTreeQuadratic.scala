package com.oranda.treelogui

import treelog.QuadraticRootsExample._

/**
 * A [[com.oranda.treelogui.LogTreeItem LogTreeItem]] that forms its `DescribedComputation`
 * using the algorithm in [[treelog.QuadraticRootsExample QuadraticRootsExample]].
 */
object LogTreeQuadratic extends LogTreeItemWithoutAnnotations[Double] {
  override def result: DescribedComputation[Double] = root(Parameters(2, 5, 3))
}
