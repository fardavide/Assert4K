plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    val easyGradle = "1.5-alpha-26" // Released: Jun 09, 2020

    implementation("studio.forface.easygradle:dsl:$easyGradle")
}
