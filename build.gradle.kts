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

// More info: https://medium.com/@stpatrck/publish-an-android-library-to-github-packages-8dfff3ececcb

plugins {
  java
  kotlin("jvm") version "1.4.10"
  id("maven-publish")
  id("org.jetbrains.dokka") version "1.4.10.2"
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

/** Artifact groupId. */
group = "com.developerlife"

/** Artifact version. Note that "SNAPSHOT" in the version is not supported by bintray. */
version = "1.0"

/** This is from settings.gradle.kts, is "color-console". */
val myArtifactId: String = rootProject.name

/** This is defined above as `group`, is "com.developerlife". */
val myArtifactGroup: String = project.group.toString()

/** This is defined above as `version`, is "1.0". */
val myArtifactVersion: String = project.version.toString()

/** My GitHub username. */
val myGithubUsername = "nazmulidris"
val myGithubDescription = "ANSI colored console log output"
val myGithubHttpUrl = "https://github.com/${myGithubUsername}/${myArtifactId}"
val myGithubIssueTrackerUrl = "https://github.com/${myGithubUsername}/${myArtifactId}/issues"
val myLicense = "Apache-2.0"
val myLicenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"

val myDeveloperName = "Nazmul Idris"

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

// More info on `publishing`:
//   https://docs.gradle.org/current/userguide/publishing_maven.html#publishing_maven:resolved_dependencies
// More info on authenticating with personal access token (myDeveloperId and myArtifactName must be lowercase):
//   https://docs.github.com/en/packages/guides/configuring-gradle-for-use-with-github-packages#authenticating-to-github-packages
publishing {
  repositories {
    maven {
      name = "GitHubPackages"
      url = uri("https://maven.pkg.github.com/${myGithubUsername}/${myArtifactId}")
      credentials {
        username = System.getenv("GITHUB_PACKAGES_USERID")
        password = System.getenv("GITHUB_PACKAGES_PUBLISH_TOKEN")
      }
    }
  }
}

publishing {
  publications {
    register("gprRelease", MavenPublication::class) {
      groupId = myArtifactGroup
      artifactId = myArtifactId
      version = myArtifactVersion

      from(components["java"])

      artifact(sourcesJar)
      artifact(dokkaJavadocJar)

      pom {
        packaging = "jar"
        name.set(myArtifactId)
        description.set(myGithubDescription)
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
            id.set(myGithubUsername)
            name.set(myDeveloperName)
          }
        }
      }

    }
  }
}
