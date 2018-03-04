

sealed trait List[+A]
object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty)
      Nil
    else
      Cons(as.head, apply(as.tail: _*))

  def tail[A](ls: List[A]): List[A] = ls match {
    case Nil => Nil
    case Cons(x, xs) => xs
  }

  def head[A](ls: List[A]): A = ls match {
    case Cons(x, xs) => x
  }

  def setHead[A](ls: List[A], h: A) = Cons(h, tail(ls))

  def drop[A](ls: List[A], n: Int): List[A] =
    if (n > 1)
      drop(tail(ls), n-1)
    else if (n == 1)
      tail(ls)
    else
      Nil

  def dropWhile[A](as: List[A])(f: A => Boolean): List[A] = as match {
    case Cons(h, t) if f(h) => dropWhile(t)(f)
    case _ => as
  }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def length[A](as: List[A]): Int = foldRight(as, 0)((a,b) => b+1)

  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
    def fold(as: List[A], f: (B,A) => B): B =
      if (as == Nil || length(as) == 0)
        z
      else
        f(fold(tail(as), f), head(as))

    fold(as, f)
  }

  def add(as: List[Int], z: Int): List[Int] = as match {
    case Nil => Nil
    case Cons(h, t) => Cons(h + z, add(t, z))
  }

  def startsWith[A](ls: List[A], sub: List[A]): Boolean = (ls, sub) match {
    case (_, Nil) => true
    case (Cons(h1, t1), Cons(h2, t2)) if h1 == h2 => startsWith(t1, t2)
    case _ => false
  }

  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = sup match {
    case Nil => false
    case _ if (startsWith(sup, sub)) => true
    case Cons(_, t) => hasSubsequence(t, sub)
  }


}


object Main {
  def main(args: Array[String]): Unit = {
    val xs: List[Int] = List(1,2,3,4,5,7)
    val r = List.add(xs, 2)
    print(r)

  }
}