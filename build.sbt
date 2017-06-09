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
    buildInfoOptions += BuildInfoOption.ToJson,
    // https://mvnrepository.com/artifact/org.jsoup/jsoup
    libraryDependencies += "org.jsoup" % "jsoup" % "1.10.2",
    // https://mvnrepository.com/artifact/com.vladsch.flexmark/flexmark-all
    libraryDependencies += "com.vladsch.flexmark" % "flexmark-all" % "0.19.6"
  )
  .dependsOn(documentation)

lazy val documentation = project
  .settings(
    managedResources in Compile ++= {
      (baseDirectory.value * "*.md").get
    }
  )
