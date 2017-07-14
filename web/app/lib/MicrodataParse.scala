package lib

import controllers.WorkItem
import org.jsoup.nodes.Document

/**
  * Created by me on 15/07/2017.
  */
object MicrodataParse {
  def parseItems(document: Document): List[WorkItem] = {
    import scala.collection.JavaConverters._

    for {
      product <- document.select(".h-product").asScala.toList
      name <- product.select(".p-name").asScala.headOption.map(_.text().trim)
      price <- product.select(".p-price").asScala.headOption.map(_.text().trim)
      url <- product.select("a.u-url, a[rel='bookmark']").asScala.headOption.map(_.attr("href"))
    } yield
      WorkItem(
        url = url,
        title = name,
        price = price
      )

  }
}
