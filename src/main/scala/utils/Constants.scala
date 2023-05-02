package utils

object Constants {

  val BreakLine: String = "\n"
  val Underscore: String = "_"
  val CommaSpaceString: String = ", "
  val ZeroInt: Int = 0
  val DefaultAttempts: Int = 10
  val EmptyCharList: List[Char] = List()

  val FilePath: String = "src/main/resources/words_alpha.txt"
  val DimMessage: String = s"\nEnter the length of the word: "

  val Quit: String = "quit"
  val NextLetterMessage: String = "\nEnter the next letter (type 'quit' to exit): "
  val VictoryMessage: String = "Well Done"
  val LooseMessage: String => String= (word: String) =>" You died \n" +
  " ---+\n :  |\n O  |\n-·- |\n |  |\n- - |\n  --+--" +
    s"\nThe secret word was $word"

  val ValidCharacterMessage = "Please enter a valid character"
  val UsedLetterMessage = "You have already used that letter"
}

/*+
 ---+
 :  |
 O  |
-·- |
 |  |
- - |
  --+--
 */