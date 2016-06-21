TreeLog-ScalaJS
===============

This project brings together two other projects: the excellent 
[TreeLog] (https://github.com/lancewalton/treelog) from Lance and Channing Walton,
and the pretty ReactTreeView component from Øyvind Raddum Berg in the 
[scalajs-react-components](https://github.com/chandu0101/scalajs-react-components) project.

**TreeLog** is a way of displaying a program's log in a tree structure instead of the hopelessly
verbose sequential format we are usually cursed with. It is based on the Scalaz `Writer` monad. 
Although it is oriented towards algorithms with pure functions (like mathematical computations), 
it can annotate tree nodes with messages for side-effects. Read more about it 
[at typelevel](http://typelevel.org/blog/2013/10/18/treelog.html).

**ReactTreeView** is the UI part: this component supports expanding nodes, highlighting, and other 
nice features. It existed first as a [normal ReactJS component](https://github.com/chenglou/react-treeview), 
then was [adapted into ScalaJS]( 
https://github.com/chandu0101/scalajs-react-components/blob/master/core/src/main/scala/chandu0101/scalajs/react/components/ReactTreeView.scala), 
meaning you can write your tree in Scala and it will be 
converted automatically into JavaScript. See the screenshot lower down to see what it looks like. 

Implementation
==============

So, two tree projects: a match made in heaven? All TreeLog-ScalaJS really needs to do
is transform a `scalaz.Tree` into a UI `TreeItem`. The main code is in `com.oranda.treelogui`. 
There is some code here and in `demo.components` that is copied from the 
scalajs-react-components demo. The code in the `treelog` package is copied straight from 
the TreeLog project.
          
Running
=======
     
1. Clone this project (or download and unzip).
2. In the project's root, from the command-line run `sbt fastOptJS`. This will do all the Scala compilation and
 ScalaJS transpilation.
3. In your browser navigate to the project's index.html on the filesystem. (There is no need
 to run a server.) You should see:     
      
![](https://github.com/oranda/treelog-scalajs/raw/master/Screenshot-TreeLog_UI_v0.1.png)
      
      
License
=======

Licensed under

 * Apache License, Version 2.0, ([LICENSE-APACHE](LICENSE-APACHE) or http://www.apache.org/licenses/LICENSE-2.0)
 * MIT license ([LICENSE-MIT](LICENSE-MIT) or http://opensource.org/licenses/MIT)
