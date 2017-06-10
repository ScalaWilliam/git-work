package controllers

import javax.inject.Inject

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
    extends InjectedController {

  def index = Action { implicit r =>
    val doc = Jsoup.parse(contentPath.contentPath.resolve("index.html").toFile, "UTF-8")
    doc
      .select("#intro")
      .html(s"${ProblemHtml.problemHtml}<hr>${SolutionHtml.solutionHtml}")
    doc
      .select("#flow")
      .first()
      .appendElement("script")
      .attr("type", "text/vnd.graphviz")
      .attr(
        "data", {
          val src = scala.io.Source.fromInputStream(
            getClass.getResourceAsStream(example.builddoc._example_flow_dot.resourceName))
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

}
