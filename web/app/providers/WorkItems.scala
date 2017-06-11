package providers

import java.time.Instant
import javax.inject._

import akka.actor.ActorSystem
import akka.agent.Agent
import akka.stream.ActorMaterializer
import controllers.WorkItem
import play.api.libs.json.{Json, OFormat}
import providers.WorkItems.WorkItemEvent
import providers.WorkItems.WorkItemEvent.WorkItemAdded

import scala.concurrent.ExecutionContext

/**
  * Created by william on 10/6/17.
  */
@Singleton
class WorkItems @Inject()(events: Events)(implicit executionContext: ExecutionContext,
                                              actorSystem: ActorSystem) {

  private implicit val actorMaterializer = ActorMaterializer()

  val data = Agent(Set.empty[WorkItem])

  events.events
    .collect { case WorkItems.WorkItemEvent(WorkItemEvent.WorkItemAdded(_, workItem)) => workItem }
    .runForeach(i => data.send(_ + i))

  def publish(workItem: WorkItem): Unit = {
    events.writeEvent(WorkItemAdded(Instant.now(), workItem).toLine)
  }

}

object WorkItems {

  sealed trait WorkItemEvent {
    def instant: Instant
  }

  object WorkItemEvent {

    def unapply(string: String): Option[WorkItemEvent] = {
      string.split('\t').toList match {
        case _ :: "work-item-added" :: json :: Nil =>
          Some(Json.fromJson[WorkItemAdded](Json.parse(json)).get)
        case _ => None
      }
    }

    case class WorkItemAdded(instant: Instant, workItem: WorkItem) extends WorkItemEvent {
      def toLine: String = s"${instant}\twork-item-added\t${Json.toJson(this)}"
    }
    object WorkItemAdded {
      implicit val fmt: OFormat[WorkItemAdded] = Json.format[WorkItemAdded]
    }

  }

}
