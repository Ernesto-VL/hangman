package client.cli

import api.HangmanInitializer
import game.engine.HangmanEngine

object Main extends App  {

  println("Welcome to Hangman, by EVL from Habla.")
  implicit val hangmanGame: HangmanInitializer = HangmanEngine
  MainMenu.mainLoop
}
