package controllers

import java.net.URL
import javax.inject.Inject

import example.Documents
import lib.{ContentPath, MicrodataParse}
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
    doc
      .select("#intro")
      .html(Documents.Intro.Problem.html + "<hr>" + Documents.Intro.Solution.html)

    val figures = doc.select("main > figure")

    val firstFigure = figures.first()

    val workDoc = Jsoup.parse(new URL("http://work.scalawilliam.com/"), 1000)
    val workItems = MicrodataParse.parseItems(workDoc)
    workItems.foreach { workItem =>
      val cloned = firstFigure.clone()
      workItem.renderTo(cloned)
      cloned.select(".title").attr("href", workItem.url)
      firstFigure.before(cloned)
    }

    figures.remove()

    Ok(Html(doc.outerHtml()))
  }

}
