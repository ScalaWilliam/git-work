import controllers.WorkItem
import lib.MicrodataParse
import org.apache.commons.io.IOUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.scalatest.FreeSpec
import org.scalatest.Matchers._

/**
  * Created by william on 11/6/17.
  */
class MicrodataParseTest extends FreeSpec {

  "It works" in {
    val document = Jsoup.parse(IOUtils.toString(getClass.getResource("/microdata.html"), "UTF-8"))
    val workItem = WorkItem(
      url = "http://somewhere/",
      title = "Title",
      price = "$20.00",
      skills = List("Scala", "HTML")
    )

    val items = MicrodataParse.parseItems(document)
    items should have size 1
    items.head shouldEqual workItem
  }

}
