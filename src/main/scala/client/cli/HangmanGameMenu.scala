package client.cli

import api.{HangmanGame, HangmanState}
import cats.effect.IO
import client.cli.ClientConstants._
import utils.StringUtils.StringMethods

import scala.io.StdIn

/**
 * Opciones de menu
 * quit
 * jugar letra
 *
 * guardar partida
 */
object HangmanGameMenu {

  def getGameState(gameState: HangmanState): String = {
    val state = s"Word: ${gameState.secretStateWord}$BreakLine" +
      s"Successful letters: ${gameState.successfulLetters.sorted.mkString(CommaSpaceString)}$BreakLine" +
      s"Failed letters: ${gameState.failedLetters.sorted.mkString(CommaSpaceString)}$BreakLine" +
      s"Attempts left: ${gameState.attempt}"

    state
  }


  def validateUserImputLetter(letter: String): Either[String, Char] = {
    if (letter.length != 1)
      Left(OnlyOneCharacterMessage)
    else if (!letter.isLetter)
      Left(ValidCharacterMessage)
    else
      Right(letter.toLowerCase.charAt(ZeroInt))
  }

  def finalMessage(game: HangmanGame): IO[Unit] = {
    if (game.checkWinner) {
      IO(println(VictoryMessage))
    }
    else {
      IO(println(LooseMessage(game.secretWord)))
    }
  }

  def makePlay(game: HangmanGame, letter: String): IO[HangmanGame] = IO {
    val letterValidation = validateUserImputLetter(letter)
    val playedGame: HangmanGame = letterValidation.fold(
      error => {
        println(error)
        game
      },
      char => game.playLetter(char).fold(
        error => {
          println(error) // todo properly print the error
          game
        },
        newGame => {
          println(getGameState(newGame.getState))
          newGame
        }
      )
    )
    playedGame
    }

  def playLoop(implicit game: HangmanGame): IO[Unit] = {

    val userAction = IO(StdIn.readLine(NextLetterMessage).trim)

    userAction.flatMap {
      case Quit => IO()
      case Save => IO()
      case MainMenu => IO()
      case letter =>
        val playedGame = makePlay(game, letter)
        playedGame.flatMap(currentGame => {
          if (currentGame.isFinished) {
            finalMessage(currentGame)
          }
          else playLoop(currentGame)
        })
    }
  }
}