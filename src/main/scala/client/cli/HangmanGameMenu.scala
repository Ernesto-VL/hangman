package client.cli

import api.HangmanState
import game.engine.HangmanEngine
import utils.Constants._
import utils.StringUtils.StringMethods

import scala.annotation.tailrec
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
      Left("Please enter only one character")
    else if (!letter.isLetter)
      Left(ValidCharacterMessage)
    else
      Right(letter.toLowerCase.charAt(ZeroInt))
  }

  def finalMessage(game: HangmanEngine): Unit = {
    if (game.checkWinner) {
      println(VictoryMessage)
    }
    else {
      println(LooseMessage(game.secretWord))
    }
  }

  def makePlay(game: HangmanEngine, letter: String): HangmanEngine = {
    val letterValidation = validateUserImputLetter(letter)
    val playedGame = letterValidation.fold(
      error => {
        println(error)
        game
      },
      char => game.playLetter(char).fold(
        error => {
          println(error)
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

  @tailrec
  def playLoop(game: HangmanEngine): Unit = { // IO de cats effects

    val userAction = StdIn.readLine(NextLetterMessage).trim

    userAction match {
      case "quit" =>
      case "save" =>
      case "mainMenu" => MainMenu.mainLoop
      case letter =>
        val playedGame = makePlay(game, letter)
        if (playedGame.isFinished) {
          finalMessage(playedGame)
          MainMenu.mainLoop
        } else playLoop(playedGame)

    }
  }
}