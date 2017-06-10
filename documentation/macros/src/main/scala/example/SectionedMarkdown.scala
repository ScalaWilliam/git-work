package example

import com.vladsch.flexmark.ast.{Document, Heading, Node}
import com.vladsch.flexmark.util.options.MutableDataSet
import com.vladsch.flexmark.util.sequence.BasedSequence

/**
  * Created by william on 10/6/17.
  */
object SectionedMarkdown {
  def sectionToDoc(section: (Heading, List[Node])): Document = {
    val mds = new MutableDataSet()
    val doc = new Document(mds, BasedSequence.NULL)
    doc.appendChild(section._1)
    section._2.foreach(doc.appendChild)
    doc
  }
}
