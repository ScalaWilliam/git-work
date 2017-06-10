package example

import java.io.InputStreamReader

import com.vladsch.flexmark.ast.{Document, Heading, Node}
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.MutableDataSet
import example.MarkdownImpl.MarkdownDocHeading

/**
  * Created by me on 11/06/2017.
  */
object Implicits {

  implicit class RichMarkdownHeading(markdownHeading: MarkdownDocHeading) {

    def getDocument(implicit parser: Parser): Document = {
      val document: Node = {
        val is = getClass.getResourceAsStream(markdownHeading.resourceName)
        try parser.parseReader(new InputStreamReader(is))
        finally is.close()
      }

      fromNode(document)
        .map(SectionedMarkdown.sectionToDoc)
        .getOrElse {
          throw new IllegalStateException(s"Could not locate heading!")
        }
    }

    private def fromNode(node: Node): Option[(Heading, List[Node])] = {
      import collection.JavaConverters._
      node.getChildren
        .iterator()
        .asScala
        .collectFirst {
          case h: Heading if h.getAnchorRefText == markdownHeading.heading => h
        }
        .map { foundHeading =>
          val nodes = node.getChildren
            .iterator()
            .asScala
            .dropWhile(_ != foundHeading)
            .drop(1)
            .takeWhile {
              case h: Heading if h.getLevel == foundHeading.getLevel => false
              case _ => true
            }
            .toList
          (foundHeading, nodes)
        }
    }
  }
}
