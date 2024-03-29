/*
 * Copyright 2021 Nazmul Idris. All rights reserved.
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

/**
 * These ANSI colors are extensions functions that can only be applied to String or Char types.
 *
 * Here are some examples.
 *
 * val s1 = "red with white background".red().bgWhite()
 * val s2 = "yellow".yellow()
 * val s3 = "bold bright blue".bold().brightBlue()
 */

object AnsiColors {
  const val reset = "\u001b[0m"
  const val bold = "\u001b[1m"
  const val italic = "\u001b[3m"
  const val underline = "\u001b[4m"
  const val reversed = "\u001b[7m"
  const val black = "\u001b[30m"
  const val blue = "\u001b[34m"
  const val cyan = "\u001b[36m"
  const val green = "\u001b[32m"
  const val magenta = "\u001b[35m"
  const val red = "\u001b[31m"
  const val white = "\u001b[37m"
  const val yellow = "\u001b[33m"
  const val brightBlack = "\u001b[30;1m"
  const val brightBlue = "\u001b[34;1m"
  const val brightCyan = "\u001b[36;1m"
  const val brightGreen = "\u001b[32;1m"
  const val brightMagenta = "\u001b[35;1m"
  const val brightRed = "\u001b[31;1m"
  const val brightWhite = "\u001b[37;1m"
  const val brightYellow = "\u001b[33;1m"
  const val bgBlack = "\u001b[40m"
  const val bgBlue = "\u001b[44m"
  const val bgCyan = "\u001b[46m"
  const val bgGreen = "\u001b[42m"
  const val bgMagenta = "\u001b[45m"
  const val bgRed = "\u001b[41m"
  const val bgWhite = "\u001b[47m"
  const val bgYellow = "\u001b[43m"
  const val bgBrightBlack = "\u001b[40;1m"
  const val bgBrightBlue = "\u001b[44;1m"
  const val bgBrightCyan = "\u001b[46;1m"
  const val bgBrightGreen = "\u001b[42;1m"
  const val bgBrightMagenta = "\u001b[45;1m"
  const val bgBrightRed = "\u001b[41;1m"
  const val bgBrightWhite = "\u001b[47;1m"
  const val bgBrightYellow = "\u001b[43;1m"
}

fun String.bold() = "\u001b[1m${this}\u001b[0m"
fun String.italic() = "\u001b[3m${this}\u001b[0m"
fun String.underline() = "\u001b[4m${this}\u001b[0m"
fun String.reversed() = "\u001b[7m${this}\u001b[0m"
fun String.black() = "\u001b[30m${this}\u001b[0m"
fun String.blue() = "\u001b[34m${this}\u001b[0m"
fun String.cyan() = "\u001b[36m${this}\u001b[0m"
fun String.green() = "\u001b[32m${this}\u001b[0m"
fun String.magenta() = "\u001b[35m${this}\u001b[0m"
fun String.red() = "\u001b[31m${this}\u001b[0m"
fun String.white() = "\u001b[37m${this}\u001b[0m"
fun String.yellow() = "\u001b[33m${this}\u001b[0m"
fun String.brightBlack() = "\u001b[30;1m${this}\u001b[0m"
fun String.brightBlue() = "\u001b[34;1m${this}\u001b[0m"
fun String.brightCyan() = "\u001b[36;1m${this}\u001b[0m"
fun String.brightGreen() = "\u001b[32;1m${this}\u001b[0m"
fun String.brightMagenta() = "\u001b[35;1m${this}\u001b[0m"
fun String.brightRed() = "\u001b[31;1m${this}\u001b[0m"
fun String.brightWhite() = "\u001b[37;1m${this}\u001b[0m"
fun String.brightYellow() = "\u001b[33;1m${this}\u001b[0m"
fun String.bgBlack() = "\u001b[40m${this}\u001b[0m"
fun String.bgBlue() = "\u001b[44m${this}\u001b[0m"
fun String.bgCyan() = "\u001b[46m${this}\u001b[0m"
fun String.bgGreen() = "\u001b[42m${this}\u001b[0m"
fun String.bgMagenta() = "\u001b[45m${this}\u001b[0m"
fun String.bgRed() = "\u001b[41m${this}\u001b[0m"
fun String.bgWhite() = "\u001b[47m${this}\u001b[0m"
fun String.bgYellow() = "\u001b[43m${this}\u001b[0m"
fun String.bgBrightBlack() = "\u001b[40;1m${this}\u001b[0m"
fun String.bgBrightBlue() = "\u001b[44;1m${this}\u001b[0m"
fun String.bgBrightCyan() = "\u001b[46;1m${this}\u001b[0m"
fun String.bgBrightGreen() = "\u001b[42;1m${this}\u001b[0m"
fun String.bgBrightMagenta() = "\u001b[45;1m${this}\u001b[0m"
fun String.bgBrightRed() = "\u001b[41;1m${this}\u001b[0m"
fun String.bgBrightWhite() = "\u001b[47;1m${this}\u001b[0m"
fun String.bgBrightYellow() = "\u001b[43;1m${this}\u001b[0m"

fun Char.bold() = "\u001b[1m${this}\u001b[0m"
fun Char.italic() = "\u001b[3m${this}\u001b[0m"
fun Char.underline() = "\u001b[4m${this}\u001b[0m"
fun Char.reversed() = "\u001b[7m${this}\u001b[0m"
fun Char.black() = "\u001b[30m${this}\u001b[0m"
fun Char.blue() = "\u001b[34m${this}\u001b[0m"
fun Char.cyan() = "\u001b[36m${this}\u001b[0m"
fun Char.green() = "\u001b[32m${this}\u001b[0m"
fun Char.magenta() = "\u001b[35m${this}\u001b[0m"
fun Char.red() = "\u001b[31m${this}\u001b[0m"
fun Char.white() = "\u001b[37m${this}\u001b[0m"
fun Char.yellow() = "\u001b[33m${this}\u001b[0m"
fun Char.brightBlack() = "\u001b[30;1m${this}\u001b[0m"
fun Char.brightBlue() = "\u001b[34;1m${this}\u001b[0m"
fun Char.brightCyan() = "\u001b[36;1m${this}\u001b[0m"
fun Char.brightGreen() = "\u001b[32;1m${this}\u001b[0m"
fun Char.brightMagenta() = "\u001b[35;1m${this}\u001b[0m"
fun Char.brightRed() = "\u001b[31;1m${this}\u001b[0m"
fun Char.brightWhite() = "\u001b[37;1m${this}\u001b[0m"
fun Char.brightYellow() = "\u001b[33;1m${this}\u001b[0m"
fun Char.bgBlack() = "\u001b[40m${this}\u001b[0m"
fun Char.bgBlue() = "\u001b[44m${this}\u001b[0m"
fun Char.bgCyan() = "\u001b[46m${this}\u001b[0m"
fun Char.bgGreen() = "\u001b[42m${this}\u001b[0m"
fun Char.bgMagenta() = "\u001b[45m${this}\u001b[0m"
fun Char.bgRed() = "\u001b[41m${this}\u001b[0m"
fun Char.bgWhite() = "\u001b[47m${this}\u001b[0m"
fun Char.bgYellow() = "\u001b[43m${this}\u001b[0m"
fun Char.bgBrightBlack() = "\u001b[40;1m${this}\u001b[0m"
fun Char.bgBrightBlue() = "\u001b[44;1m${this}\u001b[0m"
fun Char.bgBrightCyan() = "\u001b[46;1m${this}\u001b[0m"
fun Char.bgBrightGreen() = "\u001b[42;1m${this}\u001b[0m"
fun Char.bgBrightMagenta() = "\u001b[45;1m${this}\u001b[0m"
fun Char.bgBrightRed() = "\u001b[41;1m${this}\u001b[0m"
fun Char.bgBrightWhite() = "\u001b[47;1m${this}\u001b[0m"
fun Char.bgBrightYellow() = "\u001b[43;1m${this}\u001b[0m"
