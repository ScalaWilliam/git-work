scalaVersion in ThisBuild := "2.12.2"
version in ThisBuild := "0.1-SNAPSHOT"

lazy val web = Project(
  id = "web",
  base = file("web")
).enablePlugins(PlayScala)
  .enablePlugins(WebBuildInfo)
  .settings(
    name := "git-work",
    sources in (Compile, doc) := Seq.empty,
    publishArtifact in (Compile, packageDoc) := false,
    resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
    libraryDependencies += guice,
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.0-RC1",
    buildInfoPackage := "gw",
    buildInfoOptions += BuildInfoOption.ToJson
  )
