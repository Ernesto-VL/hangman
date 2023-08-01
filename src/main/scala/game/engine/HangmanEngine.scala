package game.engine

import api.{HangmanGame, HangmanInitializer, HangmanState}
import game.engine.EngineConstants._
case class HangmanEngine(secretWord: String, successfulLetters: List[Char],
                    failedLetters: List[Char], attemptsLeft: Int) extends HangmanGame {

  private val usedLetters: List[Char] = successfulLetters ++ failedLetters

  import HangmanEngine._
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

  def validateLetter(letter: Char): Option[Error] = {
    if (!letter.isLetter)
      Some(InvalidCharacter)
    else if (usedLetters.contains(letter))
      Some(UsedAlready)
    else
      None
  }

  override def getState: HangmanState = HangmanState(wordState, successfulLetters,
    failedLetters, attemptsLeft)

  override def playLetter(letter: Char): Either[Error, HangmanEngine] = {
    if(hasLoose) {
      Left(NoMoreAttempts)
    }
    else if (checkWinner) {
      Left(GuessedAlready)
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


object HangmanEngine extends HangmanInitializer {

  trait Error
  case object InvalidCharacter extends Error
  case object UsedAlready extends Error
  case object NoMoreAttempts extends Error
  case object GuessedAlready extends Error


  override def apply(dim: Int): HangmanGame = {
    val secretWord = WordGenerator.generateWord(dim)
//    new HangmanEngine(secretWord, EmptyCharList, EmptyCharList, DefaultAttempts)
    new HangmanEngine(secretWord, EmptyCharList, EmptyCharList, 3)
  }
}
