package example

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.MutableDataSet

/**
  * Created by william on 11/6/17.
  */
object ProblemHtml {
  val problemHtml = {
    val options = new MutableDataSet

    val parser = Parser.builder(options).build
    val renderer = HtmlRenderer.builder(options).build
    val document =
      parser.parse(example.builddoc._example_intro_md.Headings.Problem)
    renderer.render(document)
  }
}
