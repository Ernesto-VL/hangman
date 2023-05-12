package api

trait HangmanGame {

  def playLetter(letter: Char): Either[String, HangmanGame]

  def isFinished: Boolean

  def checkWinner: Boolean

  def getState: HangmanState

}
