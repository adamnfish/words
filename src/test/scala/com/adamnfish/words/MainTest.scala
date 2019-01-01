package com.adamnfish.words

import org.scalatest.{FreeSpec, Matchers}


class MainTest extends FreeSpec with Matchers {
  import Main._

  "count" - {
    "works for an example" in {
      count(List('a', 'a', 'b')) shouldEqual Seq('a' -> 2, 'b' -> 1)
    }
  }

  "matches" - {
    "works for an example" in {
      val result = matches(List('a', 'a', 'b'), List("aaa", "aab", "abc"))
      result shouldEqual List("aab")
    }
  }
}
