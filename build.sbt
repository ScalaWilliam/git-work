scalaVersion in ThisBuild := "2.12.4"
version in ThisBuild := "0.1-SNAPSHOT"

libraryDependencies in ThisBuild += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

lazy val web = Project(
  id = "web",
  base = file("web")
).enablePlugins(PlayScala)
  .enablePlugins(WebBuildInfo)
  .settings(
    libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-file" % "0.13",
    name := "git-work",
    sources in (Compile, doc) := Seq.empty,
    publishArtifact in (Compile, packageDoc) := false,
    resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
    buildInfoPackage := "gw",
    buildInfoOptions += BuildInfoOption.ToJson,
    libraryDependencies += "com.vladsch.flexmark" % "flexmark-all" % "0.27.0",
    libraryDependencies += "org.jsoup" % "jsoup" % "1.10.3",
    libraryDependencies += "com.typesafe.akka" %% "akka-agent" % "2.5.6",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6",
    libraryDependencies += "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided",
    libraryDependencies += "com.softwaremill.macwire" %% "util" % "2.3.0",
    libraryDependencies += "com.softwaremill.macwire" %% "proxy" % "2.3.0"
  )
  .dependsOn(documentation)

lazy val documentation = project
  .settings(libraryDependencies += "com.vladsch.flexmark" % "flexmark-all" % "0.27.0")
