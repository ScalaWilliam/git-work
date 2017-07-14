scalaVersion in ThisBuild := "2.12.2"
version in ThisBuild := "0.1-SNAPSHOT"

libraryDependencies in ThisBuild += "org.scalatest" %% "scalatest" % "3.0.3" % "test"

lazy val web = Project(
  id = "web",
  base = file("web")
).enablePlugins(PlayScala)
  .enablePlugins(WebBuildInfo)
  .settings(
    libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-file" % "0.10",
    name := "git-work",
    sources in (Compile, doc) := Seq.empty,
    publishArtifact in (Compile, packageDoc) := false,
    resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
    libraryDependencies += guice,
    buildInfoPackage := "gw",
    buildInfoOptions += BuildInfoOption.ToJson,
    libraryDependencies += "com.vladsch.flexmark" % "flexmark-all" % "0.22.4",
    resolvers += "jitpack" at "https://jitpack.io",
    libraryDependencies += "com.github.ScalaWilliam" % "dot-to-svg-jvm" % "0.2.0",
    libraryDependencies += "net.sourceforge.plantuml" % "plantuml" % "8059",
    libraryDependencies += "org.jsoup" % "jsoup" % "1.10.3",
    libraryDependencies += "com.typesafe.akka" %% "akka-agent" % "2.5.3",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.2"
  )
  .dependsOn(documentation)

lazy val documentation = project
  .settings(libraryDependencies += "com.vladsch.flexmark" % "flexmark-all" % "0.22.4")
