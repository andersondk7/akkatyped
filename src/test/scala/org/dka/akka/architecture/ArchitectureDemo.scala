package org.dka.akka.architecture

import akka.actor.typed.ActorSystem
import com.typesafe.scalalogging.Logger
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.DurationInt

/**
 * * demonstrates
 * - actor definition (via behaviors)
 * - spawning of actors
  * note:
  * - this is not a spec in the traditional sense in that it does not * test anything and hence will not fail.
  * - it is meant as a demonstration only
  *
  */

class ArchitectureDemo extends AnyFunSpec, CallHelper[Main.Message], Matchers {

  private val logger = Logger(getClass.getName)
  implicit val delay: FiniteDuration = 500.millis
  implicit val system: ActorSystem[Main.Message] = ActorSystem(Main(), "mainSystem")

  logger.info("top level: starting app")


  logger.info("top level: starting")
  call(Main.Message.Start)
  logger.info("top level: stopping")
  call(Main.Message.Stop)
  logger.info("top level: exiting")
  assert(true)
}
