package client.cli

import api.HangmanInitializer
import cats.effect.{IO, IOApp}
import game.engine.HangmanEngine

object Main extends IOApp.Simple {

  println("Welcome to Hangman, by EVL from Habla.")
  implicit val hangmanGame: HangmanInitializer = HangmanEngine
  override def run: IO[Unit] = MainMenu.mainLoop
}
