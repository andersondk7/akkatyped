package org.dka.akka.architecture

import akka.actor.typed.ActorSystem
import com.typesafe.scalalogging.Logger

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.DurationInt

/**
 * demonstrates supervision (in this case restarting) child actors
 */

@main def SupervisorApp(): Unit = {
  val logger = Logger(getClass.getName)
  implicit val delay: FiniteDuration = 500.millis
  implicit val system: ActorSystem[Supervisor.Message] = ActorSystem(Supervisor(), "supervisedSystem")
  val caller = new CallHelper[Supervisor.Message]()

  logger.info ("top level: starting")

  caller.call(Supervisor.Message.CallChild)
  caller.call(Supervisor.Message.CallChild)
  caller.call(Supervisor.Message.FailChild)
  caller.call(Supervisor.Message.CallChild)

  logger.info ("top level: exiting")
  System.exit (0)

}
