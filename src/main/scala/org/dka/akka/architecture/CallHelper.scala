package org.dka.akka.architecture

import akka.actor.typed.ActorSystem

import scala.concurrent.duration.FiniteDuration

final class  CallHelper[T] {
  def call(message: T)
                  (implicit system: ActorSystem[T], delay: FiniteDuration): Unit = {
    system ! message
    Thread.sleep(delay.toMillis)
  }
}
