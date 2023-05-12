import utils.Constants.{DimMessage, FilePath, ZeroInt}

import scala.io.{Source, StdIn}
import scala.util.Random

object WordGenerator {

  def readWordsFile: Array[String] = {
    val bufferedSource = Source.fromFile(FilePath)
    val words = bufferedSource.getLines().toArray
    bufferedSource.close()
    words
  }
  def generateWord: String = {
    val dim = StdIn.readLine(DimMessage).trim.toInt // todo meter comprobaciones

    val wordsLengthDim = readWordsFile
      .filter(_.length == dim)

    val r = Random.between(ZeroInt, wordsLengthDim.length)
    wordsLengthDim(r)
  }
}
