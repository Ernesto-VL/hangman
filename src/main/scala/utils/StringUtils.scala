package utils

object StringUtils {

  implicit class StringMethods (val string: String) {
    def isLetter: Boolean = {
      val onlyOneLetterPattern = "^[a-zA-Z]{1}$".r
      onlyOneLetterPattern.matches(string)
    }
    def isDigit: Boolean = {
      val intPattern = "\\d+".r
      intPattern.matches(string)
    }
  }
}
