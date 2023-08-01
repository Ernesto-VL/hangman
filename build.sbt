ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.typelevel" %% "cats-effect" % "3.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
lazy val root = (project in file("."))
  .settings(
    name := "hangman"
  )
