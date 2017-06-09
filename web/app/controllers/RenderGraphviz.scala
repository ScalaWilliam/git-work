package controllers

import guru.nidi.graphviz.engine._
import guru.nidi.graphviz.engine.{Format, Graphviz}
import guru.nidi.graphviz.parse.Parser

/**
  * Created by me on 09/06/2017.
  */
object RenderGraphviz {

  Graphviz.useEngine(new FastNashornEngine(null))

  def dotToSvg(dot: String, width: Int): String = {
    this.synchronized {
      val g = Parser.read(dot)
      Graphviz.fromGraph(g).width(width).render(Format.SVG_STANDALONE).toString
    }
  }

}
