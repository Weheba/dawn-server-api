plugins {
    id("java")
    id("maven-publish")
}

group = "gg.dawn.server-api"
version = "0.0.6"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:23.0.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}
