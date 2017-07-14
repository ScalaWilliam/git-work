package example

object Documents {

  object Intro {
    val resourceName = "/example/intro.md"
    val Solution = example.MarkdownImpl.MarkdownDocHeading(resourceName, "Solution")
    val Problem = example.MarkdownImpl.MarkdownDocHeading(resourceName, "Problem")
  }

  object FlowDot {
    val resourceName = "/example/flow.dot"
  }

}
