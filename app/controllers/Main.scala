package controllers

import java.nio.file.{Files, Path, Paths}
import play.api.mvc.{Action, Controller}

class Main extends Controller {

  def index = Action {
    Ok.sendPath(Main.wwwLocation.resolve("index.html"))
  }
}

object Main {
  lazy val wwwLocation: Path = {
    List("web/dist/www", "dist/www", "www")
      .map(item => Paths.get(item))
      .find(path => Files.exists(path))
      .getOrElse {
        throw new IllegalArgumentException(s"Could not find 'www'.")
      }
  }
}
