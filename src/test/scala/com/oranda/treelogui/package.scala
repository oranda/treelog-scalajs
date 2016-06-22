package com.oranda

import chandu0101.scalajs.react.components.TreeItem

package object treelogui {
  def treesAreEqual(tree1: TreeItem, tree2: TreeItem): Boolean =
    tree1.item == tree2.item &&
      (tree1.children.zip(tree2.children)).forall(cpair => treesAreEqual(cpair._1, cpair._2))
}
