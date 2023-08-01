package api

case class HangmanState (
                          secretStateWord: String, successfulLetters: List[Char],
                          failedLetters: List[Char], attempt: Int
)
