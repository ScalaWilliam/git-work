scalaVersion in ThisBuild := "2.12.2"
version in ThisBuild := "0.1-SNAPSHOT"

lazy val web = Project(
  id = "web",
  base = file("web")
).enablePlugins(PlayScala)
  .settings(
    name := "git-work",
    sources in (Compile, doc) := Seq.empty,
    publishArtifact in (Compile, packageDoc) := false
  )
