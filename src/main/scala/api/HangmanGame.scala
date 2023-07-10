package api

import game.engine.HangmanEngine
import game.engine.HangmanEngine.Error

trait HangmanGame {

  val secretWord: String
  def playLetter(letter: Char):  Either[Error,  HangmanEngine]

  def isFinished: Boolean

  def checkWinner: Boolean

  def getState: HangmanState

}
