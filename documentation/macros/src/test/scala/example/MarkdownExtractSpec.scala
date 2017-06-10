package example

import com.vladsch.flexmark.ast.Document
import com.vladsch.flexmark.util.sequence.BasedSequence
import org.scalatest.FreeSpec
import org.scalatest.Matchers._

class MarkdownExtractSpec extends FreeSpec {

  import com.vladsch.flexmark.parser.Parser
  import com.vladsch.flexmark.util.options.MutableDataSet

  lazy private val node = {
    val options = new MutableDataSet
    val parser: Parser = Parser.builder(options).build
    parser.parse(MarkdownExtractSpec.markdown)
  }

  "It extracts correct numbers of elements" in {
    SectionedMarkdown.forNode(node).map(_._2.size) shouldEqual List(5, 1, 1)
  }

  "It extracts first title correctly" in {
    SectionedMarkdown.forNode(node)(0)._1.getAnchorRefText shouldBe "Title"
  }

  "It re-renders the section" in {
    val r = SectionedMarkdown.renderSection(SectionedMarkdown.forNode(node)(1))
    assert(r == """## Stuff
      |
      |Some more stuff
      |""".stripMargin)
  }

}

object MarkdownExtractSpec {
  val markdown = """# Title
                   |
                   |Yes
                   |
                   |## Stuff
                   |
                   |Some more stuff
                   |## Stuff 3
                   |
                   |Even more stuff""".stripMargin
}
