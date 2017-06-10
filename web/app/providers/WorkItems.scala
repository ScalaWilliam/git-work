package providers

import javax.inject._
import akka.agent.Agent
import controllers.WorkItem

import scala.concurrent.ExecutionContext

/**
  * Created by william on 10/6/17.
  */
@Singleton
class WorkItems @Inject()()(implicit executionContext: ExecutionContext) {

  val data = Agent(WorkItem.sampleItems)

}
