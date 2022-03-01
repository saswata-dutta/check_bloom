package org.saswata.checkbloom

import com.google.common.hash.{Funnel, PrimitiveSink}

object StringFunnel extends Funnel[String] {
  override def funnel(source: String, sink: PrimitiveSink): Unit = {
    sink.putUnencodedChars(source)
  }
}
