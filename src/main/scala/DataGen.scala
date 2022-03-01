package org.saswata.checkbloom

import java.io.{File, PrintWriter}
import scala.util.{Random, Using}

object DataGen {
  def main(args: Array[String]): Unit = {

    val numKeys = 50_000
    val numTest = 10_000_000

    Using.Manager { use =>
      val keySink = use(new PrintWriter(new File("keys.txt")))
      val testSink = use(new PrintWriter(new File("test.txt")))

      (0 until numKeys).foreach { _ =>
        val s = Random.alphanumeric.map(_.toUpper).take(10).mkString
        keySink.println(s)
        testSink.println(s)
      }

      (0 until (numTest - numKeys)).foreach { _ =>
        val s = Random.alphanumeric.map(_.toUpper).take(10).mkString
        testSink.println(s)
      }
    }
  }
}
