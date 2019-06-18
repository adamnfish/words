package com.adamnfish.words

import scala.io.Source
import scala.util.Try


object Main {
  def main(args: Array[String]): Unit = {
    val chars = args.head.toList
    val words = Try {
      Source.fromFile("/usr/share/dict/british-english").getLines().toList
    }.getOrElse {
      Source.fromFile("/usr/share/dict/words").getLines().toList
    }

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
    // contains the required letters
    val eligibleWords = words.filter(_.toSet.subsetOf(distinctChars))
    // contains enough of each letter
    count(chars).foldLeft(eligibleWords) { case (candidates, (char, count)) =>
      candidates.filter { word =>
        word.count(_ == char) <= count
      }
    }
  }
}
