rootProject.name = "Assert4"

include("assert4k")

enableFeaturePreview("GRADLE_METADATA")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven ("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}
