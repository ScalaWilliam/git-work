package example

import java.nio.file.{Files, Paths}

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
      q"val ${Pat.Var.Term(Term.Name(valName))} = ${Lit.String(literal)}"
    }
    q"object $name { ..$ress }"
  }
}