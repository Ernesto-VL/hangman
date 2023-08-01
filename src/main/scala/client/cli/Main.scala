package client.cli

import api.HangmanInitializer
import cats.effect.{IO, IOApp}
import client.cli.ClientConstants.WelcomeMessage
import game.engine.HangmanEngine

object Main extends IOApp.Simple {

  println(WelcomeMessage)
  implicit val hangmanGame: HangmanInitializer = HangmanEngine
  override def run: IO[Unit] = MainMenu.mainLoop
}
