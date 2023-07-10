package client.cli

import api.{HangmanGame, HangmanInitializer}
import game.engine.HangmanEngine
import utils.Constants.DimMessage

import scala.annotation.tailrec
import scala.io.StdIn

object MainMenu {

  @tailrec
  def mainLoop(implicit hangmanInitializer: HangmanInitializer): Unit = {
    println("\nWhat do you want to do?" +
      "\nOptions:" +
      "\n1: New game" +
      "\n2: Load game" +
      "\n3: Quit"
    )

    val action = StdIn.readLine("Enter your option ").trim

    action match {
      case "1" =>
        val dim = StdIn.readLine(DimMessage).trim.toInt // todo meter comprobaciones
        val game: HangmanGame = hangmanInitializer.apply(dim)
        HangmanGameMenu.playLoop(game)
        mainLoop
      case "2" =>
        println("Sorry, feature not implemented yet :)")
        mainLoop
      case "3" =>
        println("See you later alligator")
        return
      case _ =>
        println("Unknown option")
        mainLoop // todo
    }
  }

  // https://github.com/hablapps/tagless-final-tutorial/blob/master/Variation5.QDSL.ipynb
}
