package controllers

import org.jsoup.nodes.Element

/**
  * Created by william on 10/6/17.
  */
object WorkItem {
  val deficiency = WorkItem(
    title = "Improve the Tutorial: Identify deficiencies",
    price = "$15.00 USD",
    skill = "Technical writing",
    url = "https://github.com/ActionFPS/ActionFPS-Book/issues/6"
  )

  val proposal = WorkItem(
    title = "Proposal for homepage?",
    price = "$15.00 USD",
    skill = "UX / User Experience",
    url = "https://github.com/ScalaWilliam/ActionFPS/issues/362"
  )

  val sampleItems = List(deficiency, proposal)

}

case class WorkItem(url: String, title: String, price: String, skill: String) {

  def renderTo(element: Element): Unit = {
    element.select(".title").first().text(title)
    element.select(".url").first().text(url).attr("href", url)
    element.select(".price").first().text(price)
    element.select(".skill").first().text(skill)
  }

}
