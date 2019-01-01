package com.adamnfish.words

import scala.io.Source


object Main {
  def main(args: Array[String]): Unit = {
    val chars = args.head.toList
    val words = Source.fromFile("/usr/share/dict/british-english").getLines().toList
    val candidates = matches(chars, words)
      .filter(_.length >= 3)
      .sortBy(word => -1 * word.length -> word)
    println(candidates.mkString("\n"))
  }

  def count[A](as: Seq[A]): Seq[(A, Int)] = {
    as.distinct.map(a => (a, as.count(_ == a)))
  }

  def matches(chars: List[Char], words: List[String]): List[String] = {
    val distinctChars = chars.toSet
    count(chars).foldLeft(words) { case (acc, (char, count)) =>
      acc.filter { word =>
        word.count(_ == char) <= count && word.toSet.subsetOf(distinctChars)
      }
    }
  }
}
