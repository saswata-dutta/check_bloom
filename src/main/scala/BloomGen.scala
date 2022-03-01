package org.saswata.checkbloom

import com.google.common.hash.BloomFilter

import java.io.{BufferedReader, FileOutputStream, FileReader}
import scala.util.Using

object BloomGen {
  def main(args: Array[String]): Unit = {

    val numKeys = 50_000
    val falsePositives = 0.0001
    val bloom: BloomFilter[String] = BloomFilter.create(StringFunnel, numKeys, falsePositives)

    Using(new BufferedReader(new FileReader("keys.txt"))) { reader =>
      Iterator.continually(reader.readLine()).takeWhile(_ != null).foreach(bloom.put)
    }

    Using(new FileOutputStream("bloom.bin")) { out =>
      bloom.writeTo(out)
    }

    println(s"approximateElementCount = ${bloom.approximateElementCount()}")
  }
}
