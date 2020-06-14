allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }
}

apply(from = "gradle/injectJsName.gradle.kts")
