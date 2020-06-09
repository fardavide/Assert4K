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

