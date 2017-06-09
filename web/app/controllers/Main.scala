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

    doc.select("#wut").html(RenderGraphviz.dotToSvg("""graph g {a--b}""".stripMargin, 300))
    doc
      .select("#wut")
      .html(RenderGraphviz.dotToSvg(doc.select("script[type='text/vnd.graphviz']").first().html(),
                                    300))

    Ok(Html(doc.outerHtml()))
  }

}
