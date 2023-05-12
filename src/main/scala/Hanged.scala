import utils.Constants.{LooseMessage, NextLetterMessage, Quit, VictoryMessage, ZeroInt}

import scala.annotation.tailrec
import scala.io.StdIn

object Hanged {

    @tailrec
    def loop(game: Game): Unit = { // IO de cats effects
// lectura de la entrada del usuario y actualizacion del estado devuelven either distintos
      val letter = StdIn.readLine(NextLetterMessage).trim
      if (letter.toLowerCase() == Quit) { //meterlo en la comprobación de la letra
        return
      }

      val playedLetter = game.playLetter(letter)

      playedLetter match { //cambiar el patter mat por un for comprehension o el fold
        case Left(error) =>
          println(error)
          loop(game)

        case Right(newGame) =>
          println(newGame.getState)
          if (newGame.checkWinner) {
            println(VictoryMessage)
            //todo preguntar por una partida nueva
          }
          else if (newGame.attempt == ZeroInt)
            println(LooseMessage(newGame.secretWord))
          //todo preguntar por una partida nueva
          else
            loop(newGame)
        }
    }
}
//tener un val con seHaFinalizado