# color-console

Table of contents

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Colorful console logging](#colorful-console-logging)
- [How to import this dependency into gradle (using JitPack)](#how-to-import-this-dependency-into-gradle-using-jitpack)
- [Publishing this dependency into JitPack](#publishing-this-dependency-into-jitpack)
- [GitHub Packages Registry (deprecated 2021-04-12)](#github-packages-registry-deprecated-2021-04-12)
  - [How to import this in gradle](#how-to-import-this-in-gradle)
  - [How to publish this to GitHub Package Registry](#how-to-publish-this-to-github-package-registry)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Colorful console logging

This is a small library that provides a simple Kotlin DSL in order to colorize console log output w/
ANSI characters.

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

## How to import this dependency into gradle (using JitPack)

You can import this dependency into your gradle projects by making the following changes to your
`build.gradle` or `build.gradle.kts` file.

1. Add this at the end of your `repositories` section.

   For `build.gradle`:

   ```groovy
   repositories {
     repositories {maven { url 'https://jitpack.io' }}
   }
   ```

   For `build.gradle.kts`:

   ```kotlin
   repositories {
     maven{
       url = uri("https://jitpack.io")
     }
   }
   ```

2. Add the dependency.

   For `build.gradle`:

   ```groovy
   dependencies { implementation 'com.github.nazmulidris:color-console:1.0.0' }
   ```

   For `build.gradle.kts`:

   ```kotlin
   dependencies { implementation ("com.github.nazmulidris:color-console:1.0.0") }
   ```

Information about this dependency on JitPack:

- You can find this dependency on JitPack
  [here](https://jitpack.io/#nazmulidris/color-console/1.0.0)
- You can find the JitPack build logs
  [here](https://jitpack.io/com/github/nazmulidris/color-console/1.0.0/build.log)

## Publishing this dependency into JitPack

Ensure that the `maven-publish` plugin is imported in `build.gradle.kts`.

```kotlin
plugins {
  java
  kotlin("jvm") version "1.6.0"
  `maven-publish`
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  testImplementation("junit", "junit", "4.12")
}

tasks {
  compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
  compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
}

publishing {
  publications {
    create<MavenPublication>("maven") {
      groupId = "com.developerlife"
      artifactId = "color-console"
      version = "1.0.1"

      from(components["java"])
    }
  }
}
```

Before you can publish this library, make sure that you can run the following commands. This will
ensure that JitPack can build this repo using gradle.

```shell
./gradlew clean
./gradlew build
./gradlew build publishToMavenLocal
```

> ⚠ Note that w/out the `publishing` section of `build.gradle.kts` you will get an error from
> JitPack even though the `./gradlew build publishToMavenLocal` will work. This error will say
> `ERROR: No build artifacts found`.
> [More info](https://sami.eljabali.org/how-to-publish-a-kotlin-library-to-jitpack/#:~:text=without%20the%20above%2C%20jitpack%20will%20down%20the%20line%20show%20in%20your%20build%20log%20error%3A%20no%20build%20artifacts%20found%2C%20while%20building%20fine).

In order to publish this repo to JitPack you have to do the following things.

1. Make the changes that you want the repo, and commit and push it. Also, make sure that the library
   can be built by JitPack using the command shown above.
2. Get all the tags for this repo from GitHub using `git pull origin`. Then you can list the
   available tags using `git tag -l`.
3. Create a new tag. To create a new tag run this command `git tag <TAG_NAME>`, where `<TAG_NAME>`
   could be something like `1.0.1`. Just make sure not to clobber any existing tag name.
4. Publish the tag to GitHub using the following command `git push --tags`.
5. Finally create a new Release for this tag using
   [GitHub web interface](https://github.com/nazmulidris/color-console/releases).

> ⚡ Note, to delete a tag from GitHub you can run this command
> `git push --delete origin <TAG_NAME> ; git pull origin`. You can delete a tag from your local repo
> using `git tag -d <TAG_NAME>; git push origin --tags`. You can't manage releases though, which
> require the use of the GitHub web UI. Here's
> [more info](https://git-scm.com/book/en/v2/Git-Basics-Tagging) on git tagging.

## GitHub Packages Registry (deprecated 2021-04-12)

### How to import this in gradle

In order to load the package for the library from GitHub Packages Registry, the
[official docs](https://docs.github.com/en/packages/guides/configuring-gradle-for-use-with-github-packages)
provide some detailed examples of the provider side of things. And you can extrapolate what the
consumer side of things might look like. The biggest thing to keep in mind is that a `read:packages`
scoped GitHub personal access token will be required by the consumer of the package (and has to be
accessible their `build.gradle` or `build.gradle.kts` file).

Make sure to provide the following environment variables before you import this package.

1. `GITHUB_PACKAGES_IMPORT_TOKEN` - this token has `read:packages` scope. This is ok to share.
2. `GITHUB_PACKAGES_USERID` - this is the GitHub username for the token. This is ok to share.

Here is more information on
[how to declare your own maven repositories](https://docs.gradle.org/current/userguide/declaring_repositories.html)
using gradle.

To import this library into your Gradle project, please add the following lines in your
`build.gradle` file in order to use this library (in Groovy).

```groovy
repositories {
  maven {
    name = "GitHubPackages"
    url = uri("https://maven.pkg.github.com/nazmulidris/color-console")
    credentials {
      username = System.getenv("GITHUB_PACKAGES_USERID") ?: "nazmulidris"
      // Safe to share the password since it is a `read:package` scoped token.
      password = System.getenv("GITHUB_PACKAGES_IMPORT_TOKEN") ?:
        "22e9ba0d47c3e9116a2f1023867a1985beebfb60"
    }
  }
}

dependencies {
  implementation 'com.developerlife:color-console:1.0'
}
```

Here's the Kotlin DSL version for `build.gradle.kts`:

```kotlin
repositories {
  maven {
    name = "GitHubPackages"
    url = uri("https://maven.pkg.github.com/nazmulidris/color-console")
    credentials {
      username = System.getenv("GITHUB_PACKAGES_USERID") ?: "nazmulidris"
      password =
        System.getenv("GITHUB_PACKAGES_IMPORT_TOKEN") ?: "22e9ba0d47c3e9116a2f1023867a1985beebfb60"
    }
  }
}

dependencies {
  implementation("com.developerlife:color-console:1.0")
}
```

### How to publish this to GitHub Package Registry

> 💡 To learn how to publish a library to GitHub Package Repository as a gradle dependency,
> [please read this tutorial](https://developerlife.com/2021/02/06/publish-kotlin-library-as-gradle-dep/).

In order to test whether this is deployed locally, you can use the following command
`./gradlew publishToMavenLocal`. This should dump the dependency on your local machine in the
following folder `$HOME/.m2/repository/com/developerlife/color-console/`. Just look for a folder
with the version number in there, which will contain all the generated artifacts.

Once you have verified that everything is working locally, it is time to publish it to the GitHub
Package Registry. Use `./gradlew publish` to do this. In order to do this the following environment
variables need to be set:

1. `GITHUB_PACKAGES_PUBLISH_TOKEN` - this token has `repo, write:packages` scope. Do **NOT** share
   this!
2. `GITHUB_PACKAGES_USERID` - this is the GitHub username for the token. This is ok to share.
