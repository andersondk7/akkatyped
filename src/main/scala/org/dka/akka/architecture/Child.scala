package org.dka.akka.architecture

import akka.actor.typed.{Behavior, PostStop, PreRestart, Signal}
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}


class Child(context: ActorContext[Child.Message]) extends AbstractBehavior[Child.Message](context) {

  override def onMessage(msg: Child.Message): Behavior[Child.Message] = {
    import Child.Message.*
    msg match {
      case Call=>
        context.log.info(s"child called")
        this

      case Fail=>
        context.log.warn("supervised failing...")
        throw new Exception("I failed")
    }
  }

  override def onSignal: PartialFunction[Signal, Behavior[Child.Message]] = {
    case PreRestart =>
      context.log.info("restarting")
      this
    case PostStop =>
      context.log.info("stopping")
      Behaviors.stopped
  }
}

object Child {
  enum Message:
    case Call, Fail


  def apply(): Behavior[Child.Message] =
    Behaviors.setup(context => new Child(context))
}

