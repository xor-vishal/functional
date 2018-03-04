//
//
//sealed trait Tree[+A] {
//  def size(): Int = this match {
//    case Leaf(_) => 1
//    case Branch(l, r) => 1 + l.size() + r.size()
//  }
//
//  def maximum(): Int = this match {
//    case Leaf(v) => v
//    case Branch(l,r) => l.maximum() max r.maximum()
//  }
//
//  def depth(): Int = this match {
//    case Leaf(_) => 0
//    case Branch(l, r) => 1 + l.depth() + r.depth()
//  }
//
//  def fold[A, B](f: A => B)(g: (B, B) => B): B = this match {
//    case Leaf(v) => f(v)
//    case Branch(l, r) => g(l.fold(f)(g), r.fold(f)(g))
//  }
//
//  def sizeViaFold[A](): Int = this.fold(_ => 1)(1 + _ + _)
//  def maxViaFold[A](): Int = this.fold(v => v)(_ max _)
//}
//
//case class Leaf[A](value: A) extends Tree[A]
//case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
//
//object Tree {
//
//}
