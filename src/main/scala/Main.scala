import utils.Constants.{DefaultAttempts, EmptyCharList}

object Main extends App {

  val secretWord = WordGenerator.generateWord
  val newGame = new Game(secretWord, EmptyCharList, EmptyCharList, DefaultAttempts)
  Hanged.loop(newGame)

  // https://github.com/hablapps/tagless-final-tutorial/blob/master/Variation5.QDSL.ipynb
}
