# color-console

Table of contents

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Colorful console logging](#colorful-console-logging)
- [How to import this from gradle](#how-to-import-this-from-gradle)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Colorful console logging

This is a small library that provides a simple Kotlin DSL in order to colorize console log output w/ ANSI characters.

Here's an example of how to use this:

```kotlin
fun main() {
  // Example 1.
  colorConsole {//this: ColorConsoleContext
    printLine {//this: MutableList<String>
      span(Purple, "word1")
      span("word2")
      span(Blue, "word3")
    }
    printLine {//this: MutableList<String>
      span(Green, "word1")
      span(Purple, "word2")
    }
    println(
        line {//this: MutableList<String>
          add(Green("word1"))
          add(Blue("word2"))
        })
  }


  // Example 2.
  val map = mapOf(
      "Key1" to "Value1",
      "Key2" to "Value2",
  )
  colorConsole {
    printLine(spanSeparator = "\n") {
      span(Red("Printing map line 1"))
      span(Green, "Printing map line 2")
      map.forEach { (label, value) ->
        // Each span is on a separate line as spanSeparator is \n
        span("${Cyan(label)}: ${White(value)}")
      }
    }
  }
}
```

## How to import this from gradle

The artifacts for this library are hosted [here on bintray](https://bintray.com/nazmulidris/maven/color-console).

To import them into your Gradle project, please add the following lines in your `build.gradle` file in order to use this
library (in Groovy).

```groovy
repositories {
    jcenter()
}

dependencies {
    implementation 'com.developerlife:color-console:1.0'
}
```

Here's the Kotlin DSL version for `build.gradle.kts`:

```kotlin
repositories {
  jcenter()
}

dependencies {
  implementation("com.developerlife:color-console:1.0")
}
```

💡 To learn how to publish a library to bintray as a gradle dependency,
[please read this tutorial](https://developerlife.com/2020/11/13/publish-kotlin-library-as-gradle-dep/).
