package org.saswata.checkbloom

import com.google.common.hash.BloomFilter

import java.io.{BufferedReader, FileOutputStream, FileReader}
import scala.util.Using

object BloomGen {
  def main(args: Array[String]): Unit = {

    val numKeys = 50_000
    val falsePositives = 0.0000001
    val bloom = BloomFilter.create[String](StringFunnel, numKeys, falsePositives)

    Using(new BufferedReader(new FileReader("test.txt"))) { reader =>
      Iterator.continually(reader.readLine()).take(numKeys).takeWhile(_ != null).foreach(bloom.put)
    }

    Using(new FileOutputStream("bloom.bin")) { fout =>
      bloom.writeTo(fout)
    }

    println(s"approximateElementCount = ${bloom.approximateElementCount()}")
  }
}
