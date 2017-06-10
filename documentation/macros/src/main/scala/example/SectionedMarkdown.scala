package example

import com.vladsch.flexmark.ast.{Document, Heading, Node}
import com.vladsch.flexmark.formatter.internal.Formatter
import com.vladsch.flexmark.util.options.MutableDataSet
import com.vladsch.flexmark.util.sequence.BasedSequence

/**
  * Created by william on 10/6/17.
  */
object SectionedMarkdown {
  def forNode(node: Node): List[(Heading, List[Node])] = {
    import collection.JavaConverters._
    node.getChildren
      .iterator()
      .asScala
      .collect {
        case h: Heading =>
          val myChildren = Iterator
            .iterate(h.getNext)(_.getNext)
            .takeWhile(_ != null)
            .takeWhile {
              case h2: Heading => h2.getLevel > h.getLevel
              case o => true
            }
            .toList
          (h, myChildren)
      }
      .toList
  }

  def renderSection(section: (Heading, List[Node])): String = {
    val mds = new MutableDataSet()
    val doc = new Document(mds, BasedSequence.NULL)
    doc.appendChild(section._1)
    section._2.foreach(doc.appendChild)

    Formatter.builder(mds).build().render(doc)
  }
}
