plugins {
    kotlin("jvm") version "1.9.10"
    id("org.jetbrains.compose") version "1.5.0"
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "MainKt"
    }
}
