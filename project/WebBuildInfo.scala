import java.util.Base64

import com.typesafe.sbt.GitPlugin
import com.typesafe.sbt.GitPlugin.autoImport.git
import sbt.Keys.{name, version}
import sbt.{AutoPlugin, Plugins, SettingKey}
import sbtbuildinfo.BuildInfoPlugin
import sbtbuildinfo.BuildInfoPlugin.autoImport.{BuildInfoKey, buildInfoBuildNumber, buildInfoKeys}

object WebBuildInfo extends AutoPlugin {

  override def requires: Plugins = BuildInfoPlugin && GitPlugin

  override def projectSettings: Seq[_root_.sbt.Def.Setting[_]] = {
    List(
      autoImport.gitCommitDescription := git.gitHeadMessage.value.map { str =>
        Base64.getEncoder.encodeToString(str.getBytes("UTF-8"))
      },
      buildInfoKeys := Seq[BuildInfoKey](
        name,
        version,
        buildInfoBuildNumber,
        git.gitHeadCommit,
        autoImport.gitCommitDescription
      )
    )
  }

  object autoImport {

    val gitCommitDescription =
      SettingKey[Option[String]]("gitCommitDescription", "Base64-encoded!")

  }

}
