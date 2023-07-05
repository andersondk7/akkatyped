package org.dka.akka.architecture

import akka.actor.typed.ActorSystem

import scala.concurrent.duration.FiniteDuration

trait CallHelper {
  type M
  def call(message: M)
                  (implicit system: ActorSystem[M], delay: FiniteDuration): Unit = {
    system ! message
    Thread.sleep(delay.toMillis)
  }
}
