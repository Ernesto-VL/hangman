package api

trait HangmanInitializer { // todo comprobar esta parte con juanma
  def apply(dim: Int): HangmanGame
}
