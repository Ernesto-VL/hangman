package client.cli

import api.{HangmanGame, HangmanInitializer}
import cats.effect.IO
import utils.Constants.DimMessage
import scala.io.StdIn
import cats.implicits._

object MainMenu {

  def mainLoop(implicit hangmanInitializer: HangmanInitializer): IO[Unit] =
    IO(println("\nWhat do you want to do?" +
      "\nOptions:" +
      "\n1: New game" +
      "\n2: Load game" +
      "\n3: Quit"
    )) >>
      IO(StdIn.readLine("Enter your option ").trim) >>={

        case "1" =>
          val dim = StdIn.readLine(DimMessage).trim.toInt // todo meter comprobaciones
          val game: HangmanGame = hangmanInitializer.apply(dim)
          HangmanGameMenu.playLoop(game) >> mainLoop
        case "2" =>
          IO(println("Sorry, feature not implemented yet.")) >> mainLoop

        case "3" =>
          IO(println("See you later alligator"))

        case _ =>
          IO(println("Unknown option")) >> mainLoop
        // todo
      }

  // https://github.com/hablapps/tagless-final-tutorial/blob/master/Variation5.QDSL.ipynb
}
