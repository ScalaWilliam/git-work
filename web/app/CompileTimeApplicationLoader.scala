import java.nio.file.{Files, Paths}

import akka.actor.ActorSystem
import play.api.ApplicationLoader.Context
import play.api.mvc.EssentialFilter
import play.filters.HttpFiltersComponents
import play.filters.cors.CORSComponents
import play.filters.gzip.GzipFilterComponents
import providers.{Events, FileEvents, InMemoryEvents, WorkItems}
import router.Routes
import com.softwaremill.macwire._
import controllers.{Main, VersionController}
import lib.ContentPath
import play.api.http.FileMimeTypes

final class CompileTimeApplicationLoader extends play.api.ApplicationLoader {
  def load(context: Context): play.api.Application =
    new CompileTimeApplicationLoaderComponents(context).application
}

final class CompileTimeApplicationLoaderComponents(context: Context)
    extends play.api.BuiltInComponentsFromContext(context)
    with HttpFiltersComponents
    with GzipFilterComponents
    with CORSComponents
    with _root_.controllers.AssetsComponents {

  private implicit def as: ActorSystem = actorSystem
  private implicit def fmt: FileMimeTypes = fileMimeTypes

  lazy val events: Events = configuration.getOptional[String]("events.file") match {
    case None => new InMemoryEvents()
    case Some(file) =>
      val path = Paths.get(file)
      require(Files.exists(path), s"${path} must exist")
      new FileEvents(path)
  }

  override def httpFilters: Seq[EssentialFilter] = Seq(corsFilter, gzipFilter)
  lazy val ContentPath: ContentPath = wire[ContentPath]
  lazy val WorkItems: WorkItems = wire[WorkItems]
  lazy val main: Main = wire[Main]
  lazy val VersionController: VersionController = wire[VersionController]
  lazy val prefix: String = "/"
  lazy val router: Routes = wire[Routes]

}
