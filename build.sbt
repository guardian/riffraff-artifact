name := "sbt-riffraff-artifact"
organization := "com.gu"

sbtPlugin := true

scalaVersion := "2.10.6"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.8.1",
  "org.joda" % "joda-convert" % "1.7" % "provided",
  "com.lihaoyi" %% "upickle" % "0.3.4",
  "org.scalamacros" %% s"quasiquotes" % "2.0.0" % "provided",
  "com.amazonaws" % "aws-java-sdk-s3" % "1.10.5.1",
  "org.eclipse.jgit" % "org.eclipse.jgit" % "4.5.0.201609210915-r"
)

publishMavenStyle := false
bintrayOrganization := Some("guardian")
bintrayRepository := "sbt-plugins"

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))

// Release
import ReleaseTransformations._
releasePublishArtifactsAction := PgpKeys.publishSigned.value
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  releaseStepTask(bintrayRelease),
  setNextVersion,
  commitNextVersion,
  pushChanges
)