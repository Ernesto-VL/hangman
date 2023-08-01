package client.cli

import api.{HangmanGame, HangmanInitializer}
import cats.effect.IO
import scala.io.StdIn
import cats.implicits._
import client.cli.ClientConstants._

object MainMenu {

  def mainLoop(implicit hangmanInitializer: HangmanInitializer): IO[Unit] =
    IO(println(MainOptionsMesssage)) >>
      IO(StdIn.readLine(UserOptionMessage).trim) >>={

        case OneString =>
          val dim = StdIn.readLine(DimMessage).trim.toInt // todo meter comprobaciones
          val game: HangmanGame = hangmanInitializer.apply(dim)
          HangmanGameMenu.playLoop(game) >> mainLoop
        case TwoString =>
          IO(println("Sorry, feature not implemented yet.")) >> mainLoop

        case ThreeString =>
          IO(println(ByeMessage))

        case _ =>
          IO(println(UnknownMessage)) >> mainLoop
        // todo
      }

  // https://github.com/hablapps/tagless-final-tutorial/blob/master/Variation5.QDSL.ipynb
}
