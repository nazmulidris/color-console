/*
 * Copyright 2020 Nazmul Idris All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package color_console_log

import java.text.SimpleDateFormat
import java.util.*

enum class Colors(val ansiCode: String) {
  ANSI_RESET("\u001B[0m"),
  Black("\u001B[30m"),
  Red("\u001B[31m"),
  Green("\u001B[32m"),
  Yellow("\u001B[33m"),
  Blue("\u001B[34m"),
  Purple("\u001B[35m"),
  Cyan("\u001B[36m"),
  White("\u001B[37m");

  operator fun invoke(content: String): String {
    return "${ansiCode}$content${ANSI_RESET.ansiCode}"
  }

  operator fun invoke(content: StringBuilder): StringBuilder {
    return StringBuilder("${ansiCode}$content${ANSI_RESET.ansiCode}")
  }
}

/**
 * DSL to print colorized console output. Here are examples of how to use this:
 * ```
 * fun main() {
 *
 *   // Example 1.
 *   colorConsole {//this: ColorConsoleContext
 *     printLine {//this: MutableList<String>
 *       span(Purple, "word1")
 *       span("word2")
 *       span(Blue, "word3")
 *     }
 *     printLine {//this: MutableList<String>
 *       span(Green, "word1")
 *       span(Purple, "word2")
 *     }
 *     println(
 *         line {//this: MutableList<String>
 *           add(Green("word1"))
 *           add(Blue("word2"))
 *         })
 *   }
 *
 *   // Example 2.
 *   val map = mapOf(
 *     "Key1" to "Value1",
 *     "Key2" to "Value2",
 *   )
 *   colorConsole {
 *     printLine(spanSeparator = "\n") {
 *       span(Red("Printing map line 1"))
 *       span(Green, "Printing map line 2")
 *       map.forEach { (label, value) ->
 *         // Each span is on a separate line as spanSeparator is \n
 *         span("${Cyan(label)}: ${White(value)}")
 *       }
 *     }
 *   }
 *
 * }
 * ```
 */
class ColorConsoleContext {
  companion object {
    fun colorConsole(block: ColorConsoleContext.() -> Unit) {
      ColorConsoleContext().apply(block)
    }
  }

  fun printLine(spanSeparator: String = ", ",
                prefixWithTimestamp: Boolean = true,
                block: MutableList<String>.() -> Unit
  ) {
    println(line(spanSeparator, prefixWithTimestamp) {
      block(this)
    })
  }

  fun line(spanSeparator: String = ", ",
           prefixWithTimestamp: Boolean = true,
           block: MutableList<String>.() -> Unit
  ): String {
    val messageFragments = mutableListOf<String>()
    block(messageFragments)
    val timeString = SimpleDateFormat("hh:mm:sa").format(Date())
    val prefix = if (prefixWithTimestamp) "[$timeString] " else ""
    return messageFragments.joinToString(spanSeparator, prefix)
  }

  /**
   * Appends all arguments to the given [MutableList].
   */
  fun MutableList<String>.span(color: Colors, text: String): MutableList<String> {
    add(color.ansiCode + text + Colors.ANSI_RESET.ansiCode)
    return this
  }

  /**
   * Appends all arguments to the given [MutableList].
   */
  fun MutableList<String>.span(text: String): MutableList<String> {
    add(text + Colors.ANSI_RESET.ansiCode)
    return this
  }
}
