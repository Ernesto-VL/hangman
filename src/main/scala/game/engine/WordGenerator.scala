package game.engine

import game.engine.EngineConstants._

import scala.io.Source
import scala.util.Random

object WordGenerator {

  def readWordsFile: Array[String] = {
    val bufferedSource = Source.fromFile(FilePath)
    val words = bufferedSource.getLines().toArray
    bufferedSource.close()
    words
  }

  def generateWord(dim: Int): String = {
    val wordsLengthDim = readWordsFile
      .filter(_.length == dim)

    val r = Random.between(ZeroInt, wordsLengthDim.length)
    wordsLengthDim(r)
  }
}
