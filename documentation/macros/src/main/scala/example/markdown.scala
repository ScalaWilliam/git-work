package example

import java.io.InputStreamReader
import java.net.URL

import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.MutableDataSet

import scala.meta._

class markdown_headings(resourcePath: String) extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any): Any = meta {

    val resourcePath = this match {
      case q"new $_(${Lit.String(arg)})" => arg
    }
    defn match {
      case q"object $name {..$rest }" =>
        Option(getClass.getResource(resourcePath)) match {
          case None =>
            val q = {
              val clz = getClass
              import collection.JavaConverters._
              java.util.Collections.list(clz.getClassLoader.getResources("")).asScala
            }
            abort(s"Resource ${resourcePath} expected to exist, does not; ${q}")
          case Some(url) =>
            val docName = Lit.String(resourcePath)
            val headings = MarkdownImpl.headingsFor(url)
            val hVals = headings.map{heading =>
              val sectionTerm = Pat.Var.Term(Term.Name(heading.getAnchorRefText.replaceAll("[^A-Za-z]","")))
              val headingName = Lit.String(heading.getAnchorRefText)
              q"""val ${sectionTerm} = example.MarkdownImpl.MarkdownDocHeading($docName, $headingName)"""
            }

            val docNameTerm = q"""val resourceName = ${docName}"""
            q"""object $name { $docNameTerm; .. $rest; .. $hVals }"""
        }
      case _ =>
        abort("@markdown_headings must annotate an object.")
    }
  }
}

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