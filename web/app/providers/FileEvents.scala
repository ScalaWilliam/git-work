package providers

import java.io.RandomAccessFile
import java.nio.file.{Path, Paths}
import javax.inject._

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.file.scaladsl.FileTailSource
import akka.stream.scaladsl._
import play.api.Configuration

/**
  * Created by william on 11/6/17.
  *
  * Inspired by https://gist.github.com/ScalaWilliam/37c4ef3c41e656a6d3c02d992f5c6191
  */
@Singleton
class FileEvents(path: Path)(implicit actorSystem: ActorSystem) extends Events {

  @Inject def this(configuration: Configuration)(implicit actorSystem: ActorSystem) = {
    this(Paths.get(configuration.get[String]("events.file")))
  }
  private implicit val actorMaterializer = ActorMaterializer()

  import concurrent.duration._

  def events: Source[String, NotUsed] = {
    FileTailSource.lines(
      path = path,
      maxLineSize = 8192,
      pollingInterval = 250.millis
    )
  }

  def writeEvent(event: String): Unit = {
    val raf = new RandomAccessFile(path.toFile, "rw").getChannel
    val lock = raf.lock()
    try {
      java.nio.file.Files
        .write(path, (event + "\n").getBytes("UTF-8"), java.nio.file.StandardOpenOption.APPEND)
    } finally {
      lock.release()
    }
  }

}
