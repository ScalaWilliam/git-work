package controllers

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import example.MarkdownImpl.MarkdownDocHeading

/**
  * Created by william on 11/6/17.
  */
object ProblemHtml {
  import example.Implicits._
  val problemHtml = {
    implicit val parser = Parser.builder().build
    val renderer = HtmlRenderer.builder().build
    val heading: MarkdownDocHeading = example.Documents.Intro.Problem
    renderer.render(heading.getDocument)
  }
}
