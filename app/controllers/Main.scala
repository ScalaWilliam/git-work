package controllers

import javax.inject.Inject

import lib.ContentPath
import play.api.mvc.{Action, Controller}

class Main @Inject()(contentPath: ContentPath) extends Controller {
  def index = Action {
    Ok.sendPath(contentPath.contentPath.resolve("index.html"))
  }
}
