import sbt.Keys._
import sbt._

name := "treelog-scalajs"

version := "0.1"

enablePlugins(ScalaJSPlugin)

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-unchecked", "-deprecation")

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies ++= Seq(
  "com.github.japgolly.fork.scalaz" %%% "scalaz-core" % "7.2.0",
  "com.github.japgolly.scalajs-react" %% "core_sjs0.6" % "0.8.3",
  "com.github.japgolly.scalajs-react" %% "extra_sjs0.6" % "0.8.3",
  "com.github.chandu0101.scalajs-react-components" %% "core_sjs0.6" % "0.4.1",
  "org.scalatest" %%% "scalatest" % "3.0.0-M15" % "test"
)

scalaJSUseRhino in Global := false

// React JS itself (Note the filenames, adjust as needed, eg. to remove addons.)
jsDependencies ++= Seq(
  "org.webjars.bower" % "react" % "15.0.2"
    /        "react-with-addons.js"
    minified "react-with-addons.min.js"
    commonJSName "React",
  "org.webjars.bower" % "react" % "15.0.2"
    /         "react-dom.js"
    minified  "react-dom.min.js"
    dependsOn "react-with-addons.js"
    commonJSName "ReactDOM",
  "org.webjars.bower" % "react" % "15.0.2"
    /         "react-dom-server.js"
    minified  "react-dom-server.min.js"
    dependsOn "react-dom.js"
    commonJSName "ReactDOMServer")

// This causes tests to be run with the headless PhantomJS instead of Node
jsDependencies += RuntimeDOM % "test"


