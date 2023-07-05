package org.dka.akka.architecture

import akka.actor.typed.{ActorSystem, Behavior, PostStop, Signal}
import akka.actor.typed.scaladsl.AbstractBehavior
import akka.actor.typed.scaladsl.ActorContext
import akka.actor.typed.scaladsl.Behaviors

class PrintRef(context: ActorContext[PrintRef.Message]) extends AbstractBehavior[PrintRef.Message](context) {

  override def onMessage(msg: PrintRef.Message): Behavior[PrintRef.Message] = {
    import PrintRef.Message.*
    msg match {
      case PrintReference =>
        val secondRef = context.spawn(Behaviors.empty[PrintRef.Message], "second-actor")
        context.log.info(s"$secondRef")
        this
    }
  }

  override def onSignal: PartialFunction[Signal, Behavior[PrintRef.Message]] = {
    case PostStop =>
      context.log.info("stopped onSignal")
      Behaviors.stopped
  }
}

object PrintRef {
  enum Message:
    case PrintReference

  def apply(): Behavior[Message] =
    Behaviors.setup(context => new PrintRef(context))
}
