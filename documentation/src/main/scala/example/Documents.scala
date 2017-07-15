package example

import org.apache.pdfbox.io.IOUtils
import org.jsoup.Jsoup

object Documents {

  case class HtmlSection(resourceName: String, sectionId: String) {
    def html: String = {
      val document =
        Jsoup.parse(new String(IOUtils.toByteArray(getClass.getResourceAsStream(resourceName))))
      val element = document.getElementById(sectionId)
      element.outerHtml()
    }
  }

  object Intro {
    val resourceName = "/example/intro.html"
    val Solution = HtmlSection(resourceName, "solution")
    val Problem = HtmlSection(resourceName, "problem")
  }

}
