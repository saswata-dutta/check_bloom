package org.saswata.checkbloom

import java.io.{File, PrintWriter}
import scala.util.{Random, Using}

object DataGen {
  def main(args: Array[String]): Unit = {

    val numTest = 10_000_000

    Using(new PrintWriter(new File("test.txt"))) { sink =>
      (0 until numTest).foreach { _ =>
        val s = Random.alphanumeric.map(_.toUpper).take(10).mkString
        sink.println(s)
      }
    }
  }
}
