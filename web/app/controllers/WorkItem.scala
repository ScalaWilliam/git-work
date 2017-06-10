package controllers

import org.jsoup.nodes.Element

/**
  * Created by william on 10/6/17.
  */
object WorkItem {
  val deficiency = WorkItem(
    id = "1704-deficiencies",
    title = "Improve the Tutorial: Identify deficiencies",
    price = "$15.00 USD",
    skill = "Technical writing",
    url = "https://github.com/ActionFPS/ActionFPS-Book/issues/6"
  )

  val proposal = WorkItem(
    id = "1705-homepage-proposal",
    title = "Proposal for homepage?",
    price = "$15.00 USD",
    skill = "UX / User Experience",
    url = "https://github.com/ScalaWilliam/ActionFPS/issues/362"
  )

  val sampleItems = Set(deficiency, proposal)
  import play.api.data._
  import play.api.data.Forms._

  implicit val form: Form[WorkItem] = Form(
    mapping("id" -> text, "url" -> text, "title" -> text, "price" -> text, "skill" -> text)(
      WorkItem.apply)(WorkItem.unapply)
  )

}

case class WorkItem(id: String, url: String, title: String, price: String, skill: String) {

  def renderTo(element: Element): Unit = {
    element.select(".title").first().text(title)
    element.select(".url").first().text(url).attr("href", url)
    element.select(".price").first().text(price)
    element.select(".skill").first().text(skill)
  }

}
