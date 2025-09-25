name := "scalendar-scala"

version := "0.1"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.19" % Test,
  "com.lihaoyi" %% "mainargs" % "0.7.6"
)

// Main class for running the application
Compile / mainClass := Some("scalendar.ScalendarApp")

enablePlugins(JavaAppPackaging)
