package org.dka.akka.architecture

import akka.actor.typed.ActorSystem
import com.typesafe.scalalogging.Logger

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.DurationInt

/**
 * application
 * demonstrates
 * - actor definition (via behaviors)
 * - spawning of actors
 */
@main def ArchitectureApp(): Unit = {
  val logger = Logger(getClass.getName)
  logger.info("top level: starting app")

  implicit val delay: FiniteDuration = 500.millis
  implicit val system: ActorSystem[Main.Message] = ActorSystem(Main(), "mainSystem")
  val caller = new CallHelper[Main.Message]()

  logger.info("top level: starting")
  caller.call(Main.Message.Start)
  logger.info("top level: stopping")
  caller.call(Main.Message.Stop)
  logger.info("top level: exiting")
  System.exit(0)
}
