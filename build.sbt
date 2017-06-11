scalaVersion in ThisBuild := "2.12.2"
version in ThisBuild := "0.1-SNAPSHOT"

libraryDependencies in ThisBuild += "org.scalatest" %% "scalatest" % "3.0.3" % "test"

lazy val web = Project(
  id = "web",
  base = file("web")
).enablePlugins(PlayScala)
  .enablePlugins(WebBuildInfo)
  .settings(
    libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-file" % "0.9",
    name := "git-work",
    sources in (Compile, doc) := Seq.empty,
    publishArtifact in (Compile, packageDoc) := false,
    resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
    libraryDependencies += guice,
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.0-RC2",
    buildInfoPackage := "gw",
    buildInfoOptions += BuildInfoOption.ToJson,
    // https://mvnrepository.com/artifact/org.jsoup/jsoup
    libraryDependencies += "org.jsoup" % "jsoup" % "1.10.2",
    // https://mvnrepository.com/artifact/com.vladsch.flexmark/flexmark-all
    libraryDependencies += "com.vladsch.flexmark" % "flexmark-all" % "0.19.6",
    resolvers += "jitpack" at "https://jitpack.io",
    libraryDependencies += "com.github.ScalaWilliam" % "dot-to-svg-jvm" % "0.2.0",
    libraryDependencies += "net.sourceforge.plantuml" % "plantuml" % "8059",
    // https://mvnrepository.com/artifact/org.jsoup/jsoup
    libraryDependencies += "org.jsoup" % "jsoup" % "1.10.2",
    libraryDependencies += "com.typesafe.akka" %% "akka-agent" % "2.5.2",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.0-RC2"
  )
  .dependsOn(documentation)

lazy val documentation = project
  .settings(metaMacroSettings)
  .dependsOn(documentationMacros)
  .settings(
    // https://stackoverflow.com/questions/17134244/reading-resources-from-a-macro-in-an-sbt-project
    unmanagedClasspath in Compile ++= (unmanagedResources in Compile).value
    // this is interesting too: https://stackoverflow.com/a/21516954/2789308
  )

lazy val documentationMacros = project
  .in(file("documentation/macros"))
  .settings(metaMacroSettings)
  .settings(
    libraryDependencies += "org.scalameta" %% "scalameta" % "1.8.0",
    libraryDependencies += "com.vladsch.flexmark" % "flexmark-all" % "0.19.6"
  )

lazy val metaMacroSettings: Seq[Def.Setting[_]] = Seq(
  // New-style macro annotations are under active development.  As a result, in
  // this build we'll be referring to snapshot versions of both scala.meta and
  // macro paradise.
  resolvers += Resolver.sonatypeRepo("releases"),
  resolvers += Resolver.bintrayRepo("scalameta", "maven"),
  // A dependency on macro paradise 3.x is required to both write and expand
  // new-style macros.  This is similar to how it works for old-style macro
  // annotations and a dependency on macro paradise 2.x.
  addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M9" cross CrossVersion.full),
  scalacOptions += "-Xplugin-require:macroparadise",
  // temporary workaround for https://github.com/scalameta/paradise/issues/10
  scalacOptions in (Compile, console) := Seq() // macroparadise plugin doesn't work in repl yet.
)
