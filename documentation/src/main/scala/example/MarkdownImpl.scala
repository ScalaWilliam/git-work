package example

import java.io.InputStreamReader
import java.net.URL

import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.MutableDataSet

object MarkdownImpl {

  case class MarkdownDocHeading(resourceName: String, heading: String)

  def headingsFor(url: URL): List[Heading] = {
    val parsed = {
      val options = new MutableDataSet
      val parser: Parser = Parser.builder(options).build
      parser.parseReader(new InputStreamReader(url.openStream()))
    }
    import collection.JavaConverters._
    parsed.getChildren.asScala.collect {
      case h: Heading => h
    }.toList
  }

}