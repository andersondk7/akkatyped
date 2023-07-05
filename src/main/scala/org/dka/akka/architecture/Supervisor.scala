package org.dka.akka.architecture

import akka.actor.typed.{Behavior, PostStop, PreRestart, Signal, SupervisorStrategy}
import akka.actor.typed.scaladsl.AbstractBehavior
import akka.actor.typed.scaladsl.{ActorContext, Behaviors}



class Supervisor(context: ActorContext[Supervisor.Message]) extends AbstractBehavior[Supervisor.Message](context) {
  import Supervisor.Message.*

  override def onMessage(msg: Supervisor.Message): Behavior[Supervisor.Message] = {
    msg match {
      case CallChild =>
        child ! Child.Message.Call
        this
      case FailChild =>
        child ! Child.Message.Fail
        this

      case Stop =>
        context.log.info("stopped")
        Behaviors.stopped
    }
  }

  private val child = context.spawn(
    Behaviors.supervise(Child()).onFailure(SupervisorStrategy.restart), name = "supervised")

}

object Supervisor {
  enum Message:
    case CallChild, FailChild, Stop

  def apply(): Behavior[Supervisor.Message] =
    Behaviors.setup(context => new Supervisor(context))

}