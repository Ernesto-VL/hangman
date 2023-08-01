package client.cli

object ClientConstants {
  val BreakLine: String = "\n"
  val CommaSpaceString: String = ", "
  val ZeroInt: Int = 0
  val OneString: String = "1"
  val TwoString: String = "2"
  val ThreeString: String = "3"

  val Quit = "quit"
  val Save = "save"
  val MainMenu = "mainMenu"

  val WelcomeMessage: String = "Welcome to Hangman, by EVL from Habla."
  val OnlyOneCharacterMessage: String = "Please enter only one character"
  val VictoryMessage: String = "Well Done"
  val LooseMessage: String => String = (word: String) => "\n\nYou died \n" +
    " ---+\n :  |\n O  |\n-·- |\n |  |\n- - |\n  --+--" +
    s"\nThe secret word was $word"
  val NextLetterMessage: String = "\nEnter the next letter (type 'menu' to go to menu): "
  val ValidCharacterMessage = "Please enter a valid character"


  // MainMenu
  val MainOptionsMesssage: String = "\nWhat do you want to do?" +
    "\nOptions:" +
    "\n1: New game" +
    "\n2: Load game" +
    "\n3: Quit"
  val DimMessage: String = s"\nEnter the length of the word: "
  val ByeMessage: String = "See you later alligator"
  val UnknownMessage: String = "Unknown option"
  val UserOptionMessage: String = "Enter your option "
  val Message: String = ""

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