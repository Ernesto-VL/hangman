package api

import game.engine.{HangmanEngine, WordGenerator}
import utils.Constants.{DefaultAttempts, EmptyCharList}

trait HangmanInitializer {
  def createNewGame(dim: Int): HangmanEngine = {
    val secretWord = WordGenerator.generateWord(dim)
    new HangmanEngine(secretWord, EmptyCharList, EmptyCharList, DefaultAttempts)
  }
}
