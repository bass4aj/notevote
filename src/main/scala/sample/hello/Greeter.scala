package sample.hello

import akka.actor.Actor
import akka.event.LoggingReceive

object Greeter {
  case object Greet
  case object Done
}

class Greeter extends Actor {
  def receive = LoggingReceive{
    case Greeter.Greet =>
      println("Hello World!")
      sender() ! Greeter.Done
  }
}