package controllers

import javax.inject.Inject

import lib.ContentPath
import play.api.http.FileMimeTypes
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

class Main @Inject()(contentPath: ContentPath)(implicit executionContext: ExecutionContext,
                                               fileMimeTypes: FileMimeTypes,
                                               controllerComponents: ControllerComponents)
    extends AbstractController(controllerComponents) {
  def index = Action {
    Ok.sendPath(contentPath.contentPath.resolve("index.html"))
  }
}
