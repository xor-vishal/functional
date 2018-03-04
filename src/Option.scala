sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = this match {
    case Some(x) => Some(f(x))
    case None => None
  }
  def flatMap[B](f: A => Option[B]): Option[B] = this match {
    case Some(x) => f(x)
    case None => None
  }

  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(x) => x
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
    case None => ob
    case _ => this
  }

  def filter(f: A => Boolean): Option[A] =
    flatMap((a: A) =>
      if (f(a))
        Some(a)
      else
        None
    )
//  this match {
//    case Some(x) if f(x) => Some(x)
//    case _ => None
//  }


}
case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

object Option {

  def Try[A](a: => A): Option[A] = try {
    Some(a)
  } catch {
    case e: Exception => None
  }

  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A,B) => C): Option[C] =
    a.flatMap(va => b.map(vb => f(va, vb)))

//  def sequence[A](a: scala.collection.immutable.List[Option[A]]): Option[List[A]] =
//    a.foldRight[Option[List[A]]](Some(Nil))((oa, ob) => map2(oa, ob)(_ :: _))

  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty)
      None
    else
      Some(xs.sum / xs.length)

  def variance(xs: Seq[Double]): Option[Double] =
    mean(xs).flatMap(m => mean(xs.map(x => math.pow(x-m, 2))))
}