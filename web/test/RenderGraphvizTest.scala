import org.scalatest.{FreeSpec, Ignore}
import org.scalatest.Matchers._

@Ignore
class RenderGraphvizTest extends FreeSpec {

  val dot =
    """
      |       graph {
      |    { rank=same; white}
      |    { rank=same; cyan; yellow; pink}
      |    { rank=same; red; green; blue}
      |    { rank=same; black}
      |
      |    white -- cyan -- blue
      |    white -- yellow -- green
      |    white -- pink -- red
      |
      |    cyan -- green -- black
      |    yellow -- red -- black
      |    pink -- blue -- black
      |}
      |
      |
    """.stripMargin
  "it works" in {
    Iterator.from(0).take(500).foreach { i =>
      println(s"Doing ${i}...")
      val r = controllers.RenderGraphviz.dotToSvg(dot, 300)
      println(s"${r}")
    }
  }
}
