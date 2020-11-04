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

import java.util.*
import java.lang.*

// More info: https://kotlinexpertise.com/kotlinlibrarydistibution/
// More info: https://medium.com/@sergio.igwt/publishing-a-kotlin-library-to-your-bintray-repo-using-gradle-kotlin-dsl-bdeaed54571a

plugins {
  java
  kotlin("jvm") version "1.4.10"
  // Plugins to publish this library to bintray.
  `maven-publish`
  id("com.jfrog.bintray") version "1.8.5"
  id("org.jetbrains.dokka") version "1.4.10.2"
}

group = "com.developerlife"
// Note that "SNAPSHOT" in the version is not supported by bintray.
version = "1.0"

repositories {
  mavenCentral()
  jcenter()
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

val myArtifactName = rootProject.name
val myArtifactGroup = project.group.toString()
val myArtifactVersion = project.version.toString()

val myDescription = "ANSI colored console log output"

val myGithubHttpUrl = "https://github.com/nazmulidris/color-console"
val myGithubIssueTrackerUrl = "https://github.com/nazmulidris/color-console/issues"
val myGithubRepo = "nazmulidris/color-console"
val myLicense = "Apache-2.0"
val myLicenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
val myDeveloperId = "nazmulidris"
val myDeveloperName = "Nazmul Idris"

val myBintrayUser: String = System.getenv("BINTRAY_USER")
val myBintrayApiKey: String = System.getenv("BINTRAY_API_KEY")
val myBintrayRepo = "maven"
val myBintrayUserOrg = myDeveloperId

val sourcesJar by tasks.creating(Jar::class) {
  archiveClassifier.set("sources")
  from(sourceSets.getByName("main").allSource)
  from("LICENCE.md") {
    into("META-INF")
  }
}

val dokkaJavadocJar by tasks.creating(Jar::class) {
  dependsOn(tasks.dokkaJavadoc)
  from(tasks.dokkaJavadoc.get().outputDirectory.get())
  archiveClassifier.set("javadoc")
}

// More info: https://docs.gradle.org/current/userguide/publishing_maven.html#publishing_maven:resolved_dependencies
publishing {
  publications {
    create<MavenPublication>(myArtifactName) {
      groupId = myArtifactGroup
      artifactId = myArtifactName
      version = myArtifactVersion

      from(components["java"])

      artifact(sourcesJar)
      artifact(dokkaJavadocJar)

      pom {
        packaging = "jar"
        name.set(myArtifactName)
        description.set(myDescription)
        url.set(myGithubHttpUrl)
        scm {
          url.set(myGithubHttpUrl)
        }
        issueManagement {
          url.set(myGithubIssueTrackerUrl)
        }
        licenses {
          license {
            name.set(myLicense)
            url.set(myLicenseUrl)
          }
        }
        developers {
          developer {
            id.set(myDeveloperId)
            name.set(myDeveloperName)
          }
        }
      }

    }
  }
}

// More info: https://github.com/bintray/gradle-bintray-plugin
bintray {
  user = System.getenv("BINTRAY_USER").toString()
  key = System.getenv("BINTRAY_API_KEY").toString()

  publish = true

  println("publish: $publish")
  println("myArtifactName: $myArtifactName")
  println("myArtifactVersion: $myArtifactVersion")

  setPublications(myArtifactName)

  pkg.apply {
    repo = myBintrayRepo
    name = myArtifactName
    userOrg = myBintrayUserOrg
    githubRepo = myGithubRepo
    vcsUrl = myGithubHttpUrl
    description = myDescription
    setLicenses(myLicense)
    desc = myDescription
    websiteUrl = myGithubHttpUrl
    issueTrackerUrl = myGithubIssueTrackerUrl

    version.apply {
      name = myArtifactVersion
      desc = myDescription
      released = Date().toString()
    }
  }
}
