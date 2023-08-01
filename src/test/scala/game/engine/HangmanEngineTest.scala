package game.engine
import org.scalatest.FlatSpec
class HangmanEngineTest extends FlatSpec {

  behavior of "HangmanEngine"
  val emptyList: List[Char] = List()

  it should "get the word state when no letter has been guessed" in {
    val game = new HangmanEngine("thorn", emptyList, emptyList, 1)
    val word = game.wordState
    assert(word=="_____")
  }

  it should "get the word state when a letter has been guessed" in {
    val game = new HangmanEngine("three", List('e', 'h'), emptyList, 1)
    val word = game.wordState
    assert(word=="_h_ee")
  }

  it should "get the word state when the word has been guessed" in {
    val game = new HangmanEngine("three", List('e', 'h', 't', 'r'), emptyList, 1)
    val word = game.wordState
    assert(word=="three")
  }

  it should "return not valid character message" in {
    val game = new HangmanEngine("three", List('e', 'h', 't', 'r'), emptyList, 1)
    val message = game.validateLetter('*')
    assert(message.get == "Please enter a valid character")
  }

  it should "return used letter message" in {
    val game = new HangmanEngine("three", List('e', 'h', 't', 'r'), emptyList, 1)
    val message = game.validateLetter('e')
    assert(message.get == "You have already used that letter")
  }

  it should "return nothing when the letter is valid" in {
    val game = new HangmanEngine("three", List('e', 'h', 't'), emptyList, 1)
    val message = game.validateLetter('r')
    assert(message.isEmpty)
  }

  it should "return a correct new game when the letter is in the secret word" in {
    val game = new HangmanEngine("three", List('e', 'h', 't'), emptyList, 1)
    val newGame = game.playLetter('r')
    val expectedGame = new HangmanEngine("three", List('e', 'h', 't', 'r'), emptyList, 1)
    assert(newGame.isRight)
    assert(newGame.right.get.equals(expectedGame))
  }

  it should "return a correct new game when the letter is not in the secret word" in {
    val game = new HangmanEngine("three", List('e', 'h', 't'), emptyList, 2)
    val newGame = game.playLetter('v')
    val expectedGame = new HangmanEngine("three", List('e', 'h', 't'), List('v'), 1)

    assert(newGame.isRight)
    assert(newGame.right.get.equals(expectedGame))
  }

  it should "return a correct the same game when is finished" in {
    val game = new HangmanEngine("three", List('e', 'h', 't'), emptyList, 0)
    val newGame = game.playLetter('v')

    assert(newGame.isRight)
    assert(newGame.right.get.equals(game))
  }
}
