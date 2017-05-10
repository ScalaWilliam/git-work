package lib

import java.nio.file.{Files, Path}
import javax.inject.Inject

import play.api.Environment

class ContentPath @Inject()(environment: Environment) {
  def contentPath: Path = {
    val rootPath = environment.rootPath.toPath
    val distContent = rootPath.resolve(ContentPath.DistDirName).resolve(ContentPath.ContentDirName)
    val content = rootPath.resolve(ContentPath.ContentDirName)
    if (Files.exists(distContent)) distContent
    else if (Files.exists(content)) content
    else throw new IllegalArgumentException("Could not find content path.")
  }
}

object ContentPath {
  val DistDirName = "dist"
  val ContentDirName = "www"
}
