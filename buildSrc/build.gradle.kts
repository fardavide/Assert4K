plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    val easyGradle = "1.5-beta-4" // Released: Jun 14, 2020

    implementation("studio.forface.easygradle:dsl:$easyGradle")
}
