package com.oranda.treelogui

import chandu0101.scalajs.react.components.ReactTreeView

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^.{^, _}
import org.scalajs.dom
import org.scalajs.dom.document

import scala.scalajs.js.annotation.JSExport

/**
 * The ScalaJS layout for a page of [[com.oranda.treelogui.LogTreeItem LogTreeItem]] examples,
 * using a [[com.oranda.treelogui.UIExample UIExample]] for each one.
 */
@JSExport
object TreeLogUIExamples {

  object Style {
    def pageBody = Seq(^.marginLeft := "40px")
    def pageHeader = Seq(^.paddingBottom := "40px")
    def treeViewDemo = Seq(^.display := "flex")

    def selectedContent = Seq(^.alignSelf := "center", ^.margin := "0 40px")
  }

  case class State(content: String = "")

  class Backend(t: BackendScope[_, _]) {

    val quadraticTreeSidebarId = "quadraticTreeSidebar"
    val annotatedTreeSidebarId = "annotatedTreeSidebar"

    def onItemSelectQuadratic(item: String, parent: String, depth: Int): Callback =
      onItemSelect(item, parent, depth, quadraticTreeSidebarId)

    def onItemSelectAnnotated(item: String, parent: String, depth: Int): Callback =
      onItemSelect(item, parent, depth, annotatedTreeSidebarId)

    def onItemSelect(item: String, parent: String, depth: Int, elementId: String): Callback = {
      val content =
        s"""Selected Item: $item <br>
                                  |Its Parent : $parent <br>
                                                         |Its depth:  $depth <br>
          """.stripMargin
      Callback(dom.document.getElementById(elementId).innerHTML = content)
    }

    def render = {
      <.div(Style.pageBody)(
        <.h2(Style.pageHeader)("TreeLog UI using ReactTreeView"),
          UIExample("Quadratic Roots Tree", "The log tree of calculating the roots of 2x² + 5x + 3.")(
            <.div(Style.treeViewDemo)(
              ReactTreeView(
                root = LogTreeQuadratic.treeItem,
                openByDefault = true,
                onItemSelect = onItemSelectQuadratic _,
                showSearchBox = true
              ),
              <.strong(^.id := quadraticTreeSidebarId, Style.selectedContent)
            )
          ),
          UIExample("Annotated Tree", "The log tree of sending greetings to two people. " +
              "Each log message is annotated with the UUID of the person greeted.")(
            <.div(Style.treeViewDemo)(
              ReactTreeView(
                root = LogTreePerson.treeItem,
                openByDefault = true,
                onItemSelect = onItemSelectAnnotated _,
                showSearchBox = true
              ),
              <.strong(^.id := annotatedTreeSidebarId, Style.selectedContent)
            )
          )
      )
    }
  }

  val component = ReactComponentB[Unit]("TreeLogUIExamples")
    .initialState(State())
    .renderBackend[Backend]
    .buildU

  def apply() = component()

  @JSExport
  def main(): Unit = ReactDOM.render(TreeLogUIExamples(), document.getElementById("container"))
}