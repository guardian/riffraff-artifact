name := "sbt-riffraff-artifact"
organization := "com.gu"

sbtPlugin := true

scalaVersion := "2.12.3"
sbtVersion in Global := "1.0.0"
crossSbtVersions := Seq("1.0.0", "0.13.16")

scalaCompilerBridgeSource := {
  val sv = appConfiguration.value.provider.id.version
  ("org.scala-sbt" % "compiler-interface" % sv % "component").sources
}

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.8.1",
  "org.joda" % "joda-convert" % "1.7" % "provided",
  "com.lihaoyi" %% "upickle" % "0.4.4",
  "com.amazonaws" % "aws-java-sdk-s3" % "1.10.5.1",
  "org.eclipse.jgit" % "org.eclipse.jgit" % "4.5.0.201609210915-r",
  "org.apache.httpcomponents" % "httpclient" % "4.5.3+",
  "com.jcraft" % "jsch" % "0.1.54+",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.10",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
  "org.scalamock" %% "scalamock" % "4.0.0" % "test"

)

fork in Test := false

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
  releaseStepCommandAndRemaining("^ test"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("^ publishSigned"),
  releaseStepTask(bintrayRelease),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
