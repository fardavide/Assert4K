plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    val easyGradle = "1.4-beta-4" // Released: Jun 06, 2020

    implementation("studio.forface.easygradle:dsl:$easyGradle")
}
