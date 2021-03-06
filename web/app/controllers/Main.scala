package controllers

import java.net.URL
import javax.inject.Inject

import lib.{ContentPath, MicrodataParse}
import org.apache.pdfbox.io.IOUtils
import org.jsoup.Jsoup
import play.api.http.FileMimeTypes
import play.api.mvc.{AbstractController, ControllerComponents}
import play.twirl.api.Html
import providers.WorkItems

import scala.concurrent.ExecutionContext

class Main @Inject()(contentPath: ContentPath,
                     workItems: WorkItems,
                     controllerComponents: ControllerComponents)(
    implicit executionContext: ExecutionContext,
    fileMimeTypes: FileMimeTypes)
    extends AbstractController(controllerComponents) { me =>

  def development = Action {
    Ok(
      Html(
        new String(IOUtils.toByteArray(getClass.getResourceAsStream("/example/development.html")))))
  }

  def index = Action {
    val doc = Jsoup.parse(contentPath.contentPath.resolve("index.html").toFile, "UTF-8")
    doc
      .select("#intro")
      .html("")

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
