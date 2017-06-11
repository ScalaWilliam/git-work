package modules
import java.nio.file.{Files, Paths}

import play.api.inject._
import play.api.{Configuration, Environment}
import providers.{Events, FileEvents, InMemoryEvents}

/**
  * Created by william on 11/6/17.
  */
class EventModule extends Module {

  override def bindings(environment: Environment, configuration: Configuration): List[Binding[_]] = {
    configuration.getOptional[String]("events.file") match {
      case None => List(bind[Events].to[InMemoryEvents])
      case Some(file) =>
        val path = Paths.get(file)
        require(Files.exists(path), s"${path} must exist")
        List(bind[Events].to[FileEvents])
    }
  }

}
