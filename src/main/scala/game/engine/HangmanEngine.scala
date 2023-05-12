package game.engine

import api.{HangmanGame, HangmanState}
import utils.Constants._

case class HangmanEngine(val secretWord: String, successfulLetters: List[Char],
                    failedLetters: List[Char], attemptsLeft: Int) extends HangmanGame {

  private val usedLetters: List[Char] = successfulLetters ++ failedLetters

  def wordState: String = {
    secretWord.flatMap(letter =>
      if (successfulLetters.contains(letter)) letter.toString
      else Underscore)
  }

  def hasLoose: Boolean = {
    if (attemptsLeft == 0) true
    else false
  }
  override def isFinished: Boolean = {
    hasLoose || checkWinner
  }

  override def checkWinner: Boolean = {
    secretWord.toCharArray.distinct.diff(successfulLetters).isEmpty
  }

  def validateLetter(letter: Char): Option[String] = {
    if (!letter.isLetter)
      Some(ValidCharacterMessage)
    else if (usedLetters.contains(letter))
      Some(UsedLetterMessage)
    else
      None
  }

  override def getState: HangmanState = HangmanState(wordState, successfulLetters,
    failedLetters, attemptsLeft)

  override def playLetter(letter: Char): Either[String, HangmanEngine] = {
    if (isFinished) {
      Right(this)
    }
    else {
      validateLetter(letter) match {
        case Some(error) => Left(error)
        case _ =>
          if (secretWord.contains(letter))
            Right(new HangmanEngine(secretWord, successfulLetters :+ letter, failedLetters, attemptsLeft))
          else
            Right(new HangmanEngine(secretWord, successfulLetters, failedLetters :+ letter, attemptsLeft - 1))
      }
    }
  }
}