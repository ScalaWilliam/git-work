package providers

import javax.inject._

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, OverflowStrategy}
import akka.stream.scaladsl.{Keep, Sink, Source}

import scala.collection.mutable

/**
  * Created by william on 11/6/17.
  */
@Singleton
class InMemoryEvents @Inject()()(implicit actorSystem: ActorSystem) extends Events {
  private implicit val actorMaterializer = ActorMaterializer()
  private val allEvents = mutable.Buffer.empty[String]

  private val (q, publisher) = Source
    .queue[String](5, OverflowStrategy.fail)
    .alsoTo(Sink.foreach(e => allEvents += e))
    .toMat(Sink.asPublisher(true))(Keep.both)
    .run()

  override def events: Source[String, NotUsed] =
    Source(allEvents.toList).concat(Source.fromPublisher(publisher))

  override def writeEvent(event: String): Unit = q.offer(event)

}
