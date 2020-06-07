import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.3.72"
    id("org.jetbrains.dokka") version "0.10.1"
}

group = "studio.forface"
version = "0.1"

repositories {
    mavenCentral()
    jcenter()
}

kotlin {

    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(kotlin("test-common"))
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
            }
        }
        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}

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

    // Dokka
    val dokka by getting(DokkaTask::class) {
        outputDirectory = "doc"
        outputFormat = "html"

        multiplatform {

        }
    }
}
