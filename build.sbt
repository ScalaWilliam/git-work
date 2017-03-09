scalaVersion := "2.11.8"
enablePlugins(PlayScala)
name := "git-work"
version := "0.1-SNAPSHOT"
sources in (Compile,doc) := Seq.empty
publishArtifact in (Compile, packageDoc) := false
