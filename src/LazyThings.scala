class LazyThings {

}

object LazyThings {
  def mayBeTwice(b: Boolean, i: => Int) = {
    lazy val j = i
    if (b) j + j else 0
  }

  def main(args: Array[String]): Unit = {
    mayBeTwice(true, {print("hi"); 2})
  }
}

sealed trait Stream[+A]
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))

  def toList[A](st: Stream[A]): List[A] = st match {
    case Empty => Nil
    case Cons(h, t) => h() :: toList(t())
  }
}

object Main {
  def main(args: Array[String]): Unit = {

  }
}
