import org.gradle.kotlin.dsl.publishing
import org.gradle.kotlin.dsl.version
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import studio.forface.easygradle.dsl.*

plugins {
    kotlin("multiplatform") version "1.3.72"
}

kotlin {

    jvm()
    js()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(kotlin("test-common"))
                implementation(coroutines("common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-annotations-common"))
            }
        }
        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test"))
                implementation(coroutines())
            }
        }
        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        js().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-js"))
                implementation(coroutines("js"))
            }
        }
        js().compilations["test"].defaultSourceSet {
            dependencies {
//                implementation(kotlin("test-junit"))
            }
        }
    }
}

fun coroutines(post: String? = null) =
    "org.jetbrains.kotlinx:kotlinx-coroutines-core${post?.let { "-$it" } ?: "" }:1.3.7"

tasks {

    // Kotlin
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = freeCompilerArgs + arrayOf(
                "-XXLanguage:+NewInference",
                "-Xopt-in=kotlin.RequiresOptIn"
            )
        }
    }
}

val dokka = dokka {
    outputDirectory = "doc"
    outputFormat = "html"

    multiplatform {

    }
}

apply<MavenPublishPlugin>()

val javaDocsJar = tasks.create<Jar>("javaDocsJar") {
    tasks.withType<DokkaTask>().firstOrNull()?.let { dokka ->
        dependsOn(dokka)
        from(dokka.outputDirectory)
    }
    archiveClassifier.set("javadoc")
}

val emptySourceJar = tasks.create<Jar>("emptySourcesJar") {
    archiveClassifier.set("sources")
}

group = "studio.forface"
version = "0.2.1"

afterEvaluate {
    publishing {

        publications.withType<MavenPublication> {

            artifact(javaDocsJar)
            if (name == "kotlinMultiplatform") artifact(emptySourceJar)

            pom {
                name.set("assert4k")
                scm {
                    url.set("https://github.com/4face-studi0/Assert4K")
                }
            }
        }

        repositories {
            maven(
                "https://api.bintray.com/maven/4face/4face/assert4k/;publish=1"
            ) {
                credentials {
                    username = "4face"
                    password = "psw"
                }
            }
        }
    }
}

