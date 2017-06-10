import java.time.LocalDateTime

import com.scalawilliam.dot.DotToSvg
import org.scalatest.{FreeSpec, Ignore}

//@Ignore
class RenderGraphvizTest extends FreeSpec {

  private val dot =
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
    """.stripMargin

  "it works" in {
    val renderer = DotToSvg.fromNewScriptEngine()
    Iterator.from(0).take(500).foreach { i =>
      println(s"Doing ${i}... ${LocalDateTime.now()}")
      renderer.renderDot(dot)
    }
  }
}
