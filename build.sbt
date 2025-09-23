name := "scalendar-scala"

version := "0.1"

scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.19" % Test
)

// Main class for running the application
Compile / mainClass := Some("scalendar.ScalendarApp")

enablePlugins(JavaAppPackaging)
