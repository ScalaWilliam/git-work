import java.time.Instant

import controllers.WorkItem
import org.scalatest.FreeSpec
import providers.WorkItems

/**
  * Created by william on 11/6/17.
  */
class OutputEventExampleSpec extends FreeSpec {

  "It works" in {
    val r = WorkItems.WorkItemEvent.WorkItemAdded(Instant.now(), WorkItem.proposal)
    info(s"${r.toLine}")
    val r2 = WorkItems.WorkItemEvent.WorkItemAdded(Instant.now(), WorkItem.deficiency)
    info(s"${r2.toLine}")
  }

}
