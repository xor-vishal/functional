class Sample {

}

object Sample {
  def partial1[A,B,C](a: A, f: (A,B) => C): B => C = b => f(a,b)

  def curry[A,B,C](f: (A,B) => C): A => (B => C) = (a: A) => ((b: B) => f(a,b))

  def uncurry[A,B,C](f: A => B => C): (A, B) => C = (a: A, b: B) => f(a)(b)

  def compose[A,B,C](f: B => C, g: A => B): A => C = a => f(g(a))

  def main(args: Array[String]): Unit = {
    val f = (x: Double) => Math.PI / 2 - x
    val cosf = f andThen Math.sin

    print(cosf(2))
  }
}
