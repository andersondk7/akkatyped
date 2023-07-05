package org.dka.akka.architecture
import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.AbstractBehavior
import akka.actor.typed.scaladsl.ActorContext
import akka.actor.typed.scaladsl.Behaviors


/**
 * Top level actor
 * @param context defines the messages that this actor can respond to
 */
class Main(context: ActorContext[Main.Message]) extends AbstractBehavior[Main.Message](context)  {
  import Main.Message.*

  override def onMessage(msg: Main.Message): Behavior[Main.Message] = {
    msg match {
      case Start =>
        val firstRef = context.spawn(PrintRef(), "first-actor")
        context.log.info(s"$firstRef")
        firstRef ! PrintRef.Message.PrintReference
        this

      case Stop =>
        context.log.info("stopped onMessage")
        Behaviors.stopped
    }
  }
}

object Main {
  enum Message:
    case Start, Stop

  def apply(): Behavior[Message] =
    Behaviors.setup(context => new Main(context))

}
