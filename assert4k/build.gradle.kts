import com.github.dawnwords.jacoco.badge.JacocoBadgeGenerate
import org.gradle.kotlin.dsl.version
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import studio.forface.easygradle.dsl.*

plugins {
    kotlin("multiplatform") version "1.4-M3"
    java
    jacoco
    id("com.github.dawnwords.jacoco.badge") version "0.2.0"
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
    "org.jetbrains.kotlinx:kotlinx-coroutines-core${post?.let { "-$it" } ?: "" }:1.3.7-mpp-dev-11"

tasks {

    // Kotlin
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = freeCompilerArgs + arrayOf(
                "-XXLanguage:+NewInference",
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xopt-in=kotlin.ExperimentalUnsignedTypes"
            )
        }
    }

    // Dokka
    val dokka = withType<DokkaTask> {
        outputDirectory = "doc"
        outputFormat = "html"

        multiplatform {}
    }

    // JacCoCo
    val jacocoReportsDir = "$rootDir/config/jacoco/reports"
    val jacocoXmlReport = "$jacocoReportsDir/xml"
    val jacocoHtmlReportsDir = "$jacocoReportsDir/html"
    val jacoco = withType<JacocoReport> {
        dependsOn("jvmTest")
        group = "Reporting"
        description = "Generate Jacoco coverage reports."
        val coverageSourceDirs = arrayOf(
            "commonMain/src",
            "jvmMain/src"
        )
        val classFiles = File("$buildDir/classes/kotlin/jvm/")
            .walkBottomUp()
            .toSet()
        classDirectories.setFrom(classFiles)
        sourceDirectories.setFrom(files(coverageSourceDirs))
        additionalSourceDirs.setFrom(files(coverageSourceDirs))

        executionData
            .setFrom(files("$buildDir/jacoco/jvmTest.exec"))
        reports {
            xml.isEnabled = true
            xml.destination = File(jacocoXmlReport)
            csv.isEnabled = false
            html.isEnabled = true
            html.destination = File(jacocoHtmlReportsDir)
        }
    }

    // JaCoCo badge
    val jacocoBadge = withType<JacocoBadgeGenerate> {
        jacocoBadgeGenSetting {
            jacocoReportPath = jacocoXmlReport
            readmePath = "$rootDir/README.md"
        }
    }

    register("prePublish") {
        dependsOn(dokka, jacoco, jacocoBadge)
    }
}

publish()
