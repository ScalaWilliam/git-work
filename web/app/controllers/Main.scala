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
    doc
      .select("#flow")
      .first()
      .appendElement("script")
      .attr("type", "text/vnd.graphviz")
      .attr(
        "data", {
          val src = scala.io.Source
            .fromInputStream(
              me.getClass.getResourceAsStream(example.Documents.FlowDot.resourceName))
          try src.mkString
          finally src.close()
        }
      )

    val figures = doc.select("main > figure")

    val firstFigure = figures.first()

    workItems.data.get.foreach { workItem =>
      val cloned = firstFigure.clone()
      workItem.renderTo(cloned)
      cloned.select(".title").attr("href", routes.Main.workItem(workItem.id).absoluteURL())
      firstFigure.before(cloned)
    }

    figures.remove()

    doc.select("#sign-in").html(views.html.signin.apply.body)

    Ok(Html(doc.outerHtml()))
  }

  def workItem(id: String) = Action {
    workItems.data.get().find(_.id == id) match {
      case None => NotFound("Item not found.")
      case Some(workItem) =>
        Ok(s"${workItem}")
    }
  }

  def wut = Action {
    Ok(PlantUMLToSvg.apply("XYZ")).as("image/svg+xml")
  }

  def postWorkItem = Action.apply(parse.form(WorkItem.form)) { implicit req =>
    workItems.data.send(_ + req.body)
    SeeOther(routes.Main.workItem(req.body.id).absoluteURL())
  }

  def callback = Action { req =>
    Ok(s"${req}")
  }
}
