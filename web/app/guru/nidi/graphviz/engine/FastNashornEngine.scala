package guru.nidi.graphviz.engine

import java.util.Map
import javax.script.{Compilable, ScriptEngineManager, ScriptException}

/**
  * Created by me on 09/06/2017.
  */

object FastNashornEngine {
  val ENGINE = new ScriptEngineManager().getEngineByExtension("js")
}

class FastNashornEngine(val engineInitListener: EngineInitListener)
  extends AbstractGraphvizEngine(false, engineInitListener) {

  override protected def doExecute(call: String): String =
    try FastNashornEngine.ENGINE.eval("$$prints=[]; " + call).asInstanceOf[String]
    catch {
      case e: ScriptException if e.getMessage.startsWith("abort") =>
        import collection.JavaConverters._
        try throw new GraphvizException(
          FastNashornEngine.ENGINE
            .eval("$$prints")
            .asInstanceOf[Map[Integer, AnyRef]]
            .asScala
            .values
            .mkString("\n")
        )
        catch {
          case e1: ScriptException =>
          //fall through to general exception
        }
        throw new GraphvizException("Problem executing graphviz", e)
    }


  @throws[Exception]
  override protected def doInit(): Unit = {
    FastNashornEngine.ENGINE.eval(initEnv())
    FastNashornEngine.ENGINE.eval(vizCode("1.4.4"))
  }
}
