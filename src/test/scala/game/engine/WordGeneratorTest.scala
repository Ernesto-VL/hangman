package game.engine
import org.scalatest.FlatSpec

class WordGeneratorTest extends FlatSpec {
  behavior of "WordGenerator"

  it should "return a word of the required length" in {
    val dim = 5
    assert(WordGenerator.generateWord(dim).length == dim)
  }
}
