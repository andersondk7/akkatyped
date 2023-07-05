import Dependencies._

lazy val scala3 = "3.3.0"

ThisBuild / organization := "org.dka.akka"
ThisBuild / version := "0.1.1-SNAPSHOT"
ThisBuild / scalaVersion := scala3

lazy val akka = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    name := "akka",
    libraryDependencies ++= akkaDependencies,
    Defaults.itSettings
  )
