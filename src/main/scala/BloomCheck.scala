package org.saswata.checkbloom

import com.google.common.hash.BloomFilter

import java.io.{BufferedReader, FileInputStream, FileReader}
import scala.util.Using

object BloomCheck {
  def main(args: Array[String]): Unit = {

    val bloom = Using(new FileInputStream("bloom.bin")) { fin =>
      BloomFilter.readFrom[String](fin, StringFunnel)
    }.get

    Using(new BufferedReader(new FileReader("test.txt"))) { reader =>
      val start = System.nanoTime()
      val count = Iterator.continually(reader.readLine()).takeWhile(_ != null).count(bloom.mightContain)
      val delta = System.nanoTime() - start
      println(s"Found $count matches in $delta nanos")
    }
  }
}
