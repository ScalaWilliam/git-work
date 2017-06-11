package example

import scala.meta._

class resource(val resourcePath: String) extends scala.annotation.StaticAnnotation {
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
            val docNameTerm = q"""val resourceName = ${docName}"""
            q"""object $name { $docNameTerm; .. $rest}"""
        }
      case _ =>
        abort("@resource must annotate an object.")
    }
  }
}
