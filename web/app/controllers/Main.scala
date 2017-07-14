package controllers

import javax.inject.Inject

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import example.Documents
import lib.ContentPath
import org.jsoup.Jsoup
import play.api.http.FileMimeTypes
import play.api.mvc.InjectedController
import play.twirl.api.Html
import providers.WorkItems

import scala.concurrent.ExecutionContext

class Main @Inject()(contentPath: ContentPath, workItems: WorkItems)(
    implicit executionContext: ExecutionContext,
    fileMimeTypes: FileMimeTypes)
    extends InjectedController { me =>

  def index = Action { implicit r =>
    val doc = Jsoup.parse(contentPath.contentPath.resolve("index.html").toFile, "UTF-8")

    implicit val parser = Parser.builder().build
    val renderer = HtmlRenderer.builder().build

    import example.Implicits._
    doc
      .select("#intro")
      .html(s"${renderer.render(Documents.Intro.Problem.getDocument)}<hr>${renderer.render(
        Documents.Intro.Solution.getDocument)}")

    val figures = doc.select("main > figure")

    val firstFigure = figures.first()

    workItems.data.get.foreach { workItem =>
      val cloned = firstFigure.clone()
      workItem.renderTo(cloned)
      cloned.select(".title").attr("href", workItem.url)
      firstFigure.before(cloned)
    }

    figures.remove()

    Ok(Html(doc.outerHtml()))
  }

}
