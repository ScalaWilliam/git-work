scalaVersion := "2.12.1"
enablePlugins(PlayScala)
name := "git-work"
version := "0.1-SNAPSHOT"
sources in (Compile,doc) := Seq.empty
publishArtifact in (Compile, packageDoc) := false
