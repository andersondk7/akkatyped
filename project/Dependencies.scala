import sbt.*

object Dependencies {


  private val akka_version = "2.8.3"
  private val config_version = "1.4.2"
  private val logback_version = "1.4.6"
  private val scalalogging_version = "3.9.5"
  private val scalactic_version = "3.2.15"
  private val scalatest_version = "3.2.15"

  private val akka = "com.typesafe.akka" %% "akka-actor-typed" % akka_version
  private val logging = "com.typesafe.scala-logging" %% "scala-logging" % scalalogging_version
  private val scalatic = "org.scalactic" %% "scalactic" % scalactic_version
  private val scalaTest = "org.scalatest" %% "scalatest" % scalatest_version % "it,test"


  // java libs
  private val config = "com.typesafe" % "config" % config_version
  private val logBack = "ch.qos.logback" % "logback-classic" % logback_version

  val akkaDependencies: Seq[ModuleID] = Seq(
    akka,
    config,
    logBack,
    logging,
    scalatic,
    scalaTest
  )
}

