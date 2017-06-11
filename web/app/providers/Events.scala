package providers

import akka.NotUsed
import akka.stream.scaladsl.Source

/**
  * Created by william on 11/6/17.
  *
  * Event Source
  */
trait Events {
  def events: Source[String, NotUsed]

  def writeEvent(event: String): Unit
}
