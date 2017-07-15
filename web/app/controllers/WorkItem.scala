package controllers

import org.jsoup.nodes.Element
import play.api.libs.json.{Json, OFormat}

/**
  * Created by william on 10/6/17.
  */
object WorkItem {
  val deficiency = WorkItem(
    title = "Improve the Tutorial: Identify deficiencies",
    price = "$15.00 USD",
    url = "https://github.com/ActionFPS/ActionFPS-Book/issues/6",
    skills = List("Technical writing")
  )

  val proposal = WorkItem(
    title = "Proposal for homepage?",
    price = "$15.00 USD",
    url = "https://github.com/ScalaWilliam/ActionFPS/issues/362",
    skills = List("UX")
  )

  val sampleItems = Set(deficiency, proposal)

  implicit val fmt: OFormat[WorkItem] = Json.format[WorkItem]
}

case class WorkItem(url: String, title: String, price: String, skills: List[String]) {

  def renderTo(element: Element): Unit = {
    element.select(".title").first().text(title)
    element.select(".url").first().text(url).attr("href", url)
    element.select(".price").first().text(price)
    element.select(".skill").first().text(skills.mkString(", "))
  }

}
