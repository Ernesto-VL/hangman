import utils.Constants.{BreakLine, CommaSpaceString, Underscore, UsedLetterMessage, ValidCharacterMessage, ZeroInt}
import utils.StringUtils.StringMethods

class Game (val secretWord: String, successfulLetters: List[Char],
            failedLetters: List[Char], val attempt: Int) {

  val usedLetters: List[Char] = successfulLetters ++ failedLetters

  def checkWinner: Boolean = {
    secretWord.toCharArray.distinct.diff(successfulLetters).isEmpty
  }

  def getState: String = { // esto tiene que ir fuera, es IO
    val wordToPrint = secretWord.flatMap(letter =>
      if (successfulLetters.contains(letter)) letter.toString
      else Underscore)
    val state = s"Word: $wordToPrint$BreakLine" +
      s"Successful letters: ${successfulLetters.sorted.mkString(CommaSpaceString)}$BreakLine" +
      s"Failed letters: ${failedLetters.sorted.mkString(CommaSpaceString)}$BreakLine" +
      s"Attempts: $attempt"

    state
  }

  def validateLetter(letter: String): Option[String] = { // IO meterlo en un método que devuelva left error right char. comprobar también aquí quit. Quizás mediante un either[either
    if (!letter.isLetter)
      Some(ValidCharacterMessage)
    else if (usedLetters.contains(letter.toLowerCase.charAt(ZeroInt)))
      Some(UsedLetterMessage)
    else
      None
  }

  //este metodo tiene que recibir un char, y aquí hacer comprobaciones sobre el char
  def playLetter(letter: String): Either[String, Game] ={ //comprobar aquí si se ha acabado (win or loose)
    validateLetter(letter) match {
      case Some(error) => Left(error)
      case _ =>
        if (secretWord.contains(letter))
          Right(new Game(secretWord, successfulLetters :+ letter.charAt(0), failedLetters, attempt))
        else
          Right(new Game(secretWord, successfulLetters, failedLetters :+ letter.charAt(0), attempt - 1))
    }
  }
}