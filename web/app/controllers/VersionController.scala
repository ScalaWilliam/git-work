package controllers

import java.util.Base64
import javax.inject._

import play.api.libs.json.{JsObject, JsString, Json}
import play.api.mvc.{AbstractController, Action, Controller, ControllerComponents}

import scala.concurrent.ExecutionContext

/**
  * Created by William on 01/01/2016.
  */
@Singleton
class VersionController @Inject()(controllerComponents: ControllerComponents)(
    implicit executionContext: ExecutionContext)
    extends AbstractController(controllerComponents) {

  def version = Action {
    val parsedJson = Json.parse(gw.BuildInfo.toJson).asInstanceOf[JsObject]
    val two = JsObject(
      VersionController.commitDescription
        .map(d => "gitCommitDescription" -> JsString(d))
        .toSeq)
    Ok(parsedJson ++ two)
  }

}

object VersionController {
  val commitDescription: Option[String] = {
    gw.BuildInfo.gitCommitDescription.map { encoded =>
      new String(Base64.getDecoder.decode(encoded), "UTF-8")
    }
  }
}
