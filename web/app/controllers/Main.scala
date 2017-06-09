package controllers

import java.io.InputStreamReader
import javax.inject.Inject

import lib.ContentPath
import org.jsoup.Jsoup
import play.api.http.FileMimeTypes
import play.api.mvc.{AbstractController, ControllerComponents}
import play.twirl.api.Html

import scala.concurrent.ExecutionContext

class Main @Inject()(contentPath: ContentPath)(implicit executionContext: ExecutionContext,
                                               fileMimeTypes: FileMimeTypes,
                                               controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {

  def index = Action {
    val doc = Jsoup.parse(contentPath.contentPath.resolve("index.html").toFile, "UTF-8")

    import com.vladsch.flexmark.html.HtmlRenderer
    import com.vladsch.flexmark.parser.Parser
    import com.vladsch.flexmark.util.options.MutableDataSet
    val options = new MutableDataSet

    val parser = Parser.builder(options).build
    val renderer = HtmlRenderer.builder(options).build
    val document =
      parser.parseReader(new InputStreamReader(getClass.getResourceAsStream("/intro.md")))
    val introHtml = renderer.render(document)

    doc.select("#intro").html(introHtml)
    doc
      .select("#flow")
      .first()
      .appendElement("script")
      .attr("type", "text/vnd.graphviz")
      .attr("data", {
        val src = scala.io.Source.fromInputStream(getClass.getResourceAsStream("/flow.dot"))
        try src.mkString
        finally src.close()
      })

    Ok(Html(doc.outerHtml()))
  }

}
