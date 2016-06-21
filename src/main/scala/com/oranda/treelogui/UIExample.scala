package com.oranda.treelogui

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js

/**
 * The ScalaJS UI layout for a single `LogTreeItem` example, including the tree
 * itself, a title, and a description.
 */
object UIExample {

  object Style {
    val pageBodyContent = Seq(^.borderRadius := "2px",
      ^.boxShadow := "0 1px 4px rgba(223, 228, 228, 0.79)",
      ^.maxWidth := "1024px")

    val contentDemo = Seq(^.paddingBottom := "40px")

    val title = Seq(^.paddingBottom := "5px")

    val description = Seq(^.paddingBottom := "10px")
  }

  case class Backend($: BackendScope[Props, _]){
    def render(P: Props, C: PropsChildren) = {
      <.div(
        P.title.nonEmpty ?= <.h3(P.title, Style.title),
        P.description.nonEmpty ?= <.div(P.description, Style.description),
        <.div(Style.pageBodyContent)(
          <.div(Style.contentDemo, ^.key := "dan")(
            C
          )
        )
      )
    }
  }

  val component = ReactComponentB[Props]("treelogexample")
    .renderBackend[Backend]
    .build

  case class Props(title: String, description: String)

  def apply(
    title:    String,
    description:    String,
    ref:      js.UndefOr[String] = "",
    key:      js.Any = {})
    (children: ReactNode*) =
    component.set(key, ref)(Props(title, description), if (children.size == 1) children.head else children)
}