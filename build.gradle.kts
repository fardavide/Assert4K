allprojects {
    repositories {
        mavenCentral()
        jcenter()
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
    }
}

apply(from = "gradle/injectJsName.gradle.kts")
