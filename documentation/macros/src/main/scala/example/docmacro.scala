package example

import java.io.InputStreamReader
import java.nio.file.{Files, Paths}

import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.MutableDataSet

import scala.collection.immutable.Seq
import scala.meta._

class docmacro extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val clz = getClass
    import collection.JavaConverters._
    val optionalExampleDir = java.util.Collections.list(clz.getClassLoader.getResources("")).asScala.find(_.toString.endsWith("/example/"))
    optionalExampleDir match {
      case None =>
        throw new IllegalStateException(s"Not where /example/ is a resource.")
      case Some(root) =>
        val staticResources = Files.list(Paths.get(root.toURI)).iterator().asScala
          .map{ path =>
            path.toString.substring(Paths.get(root.toURI).getParent.toString.length)
          }.map{DocMacroImpl.StaticResource}
          .toList

        defn match {
          case q"object $name { ..$stats }" =>
            DocMacroImpl.expand(name, stats, staticResources)
          case _ =>
            abort("@main must annotate an object.")
        }
    }
  }
}

object DocMacroImpl {

  case class StaticResource(resourcePath: String)
  def expand(name: Term.Name, stats: Seq[Stat], rs: List[StaticResource]): Defn.Object = {
    val main = q"def main(args: Array[String]): Unit = { ..$stats }"
    val ress = rs.map { r =>
      val valName = r.resourcePath.replaceAll("[^A-Za-z]", "_")
      val literal = r.resourcePath

      val objectName = Term.Name(valName)
      val resourceName = q"""val resourceName = ${Lit.String(literal)}"""
      val headingsO = if ( literal.endsWith(".md") ) Option {
        val headings = {
          val parsed = {
            val options = new MutableDataSet
            val parser: Parser = Parser.builder(options).build
            parser.parseReader(new InputStreamReader(getClass.getResourceAsStream(r.resourcePath)))
          }
          val sections = SectionedMarkdown.forNode(parsed)

          sections.map { case s @ (h, _) =>
            val md = SectionedMarkdown.renderSection(s)
            val sectionTerm = Pat.Var.Term(Term.Name(h.getAnchorRefText.replaceAll("[^A-Za-z]","")))
            q"""val $sectionTerm = ${Lit.String(md)}"""
          }

        }
        q"""object Headings { ..$headings }"""
      } else None

      q"object $objectName { $resourceName; ..$headingsO }"
    }
    q"object $name { ..$ress }"
  }
}