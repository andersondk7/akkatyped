package org.dka.akka.architecture

import akka.actor.typed.ActorSystem
import com.typesafe.scalalogging.Logger
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration.DurationInt

/**
 * demonstrates supervision (in this case restarting) child actors
 * note:
 *
 * - this is not a spec in the traditional sense in that it does not * test anything and hence will not fail.
 * - it is meant as a demonstration only
 *
 */

class SupervisorDemo extends AnyFunSpec, CallHelper[Supervisor.Message], Matchers {

  val logger = Logger(getClass.getName)
  implicit val delay: FiniteDuration = 500.millis
  implicit val system: ActorSystem[Supervisor.Message] = ActorSystem(Supervisor(), "supervisedSystem")

  logger.info ("top level: starting")

  call(Supervisor.Message.CallChild)
  call(Supervisor.Message.CallChild)
  call(Supervisor.Message.FailChild)
  call(Supervisor.Message.CallChild)

  logger.info ("top level: exiting")
  assert(true)

}
