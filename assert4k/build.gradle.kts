import com.github.dawnwords.jacoco.badge.JacocoBadgeGenerate
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import studio.forface.easygradle.publish.publish

plugins {
    val kotlinVersion = "1.4.32" // Apr 13, 2021
    val dokkaVersion = "1.4.32" // Apr 22, 2021

    java
    jacoco
    id("com.github.dawnwords.jacoco.badge") version "0.2.0"
    id("org.jetbrains.dokka") version dokkaVersion
    id("studio.forface.easy-publish") version "0.3.6"
    kotlin("multiplatform") version kotlinVersion
}

kotlin {

    jvm()
    js {
        browser()
//        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(coroutines())
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
        js().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-js"))
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
    "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3"

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
        dependsOn(task("dokkaHtml"), jacoco, jacocoBadge)
    }
}

publish {}
