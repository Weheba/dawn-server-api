plugins {
    id("dawn-server-api.java-conventions")
    id("dawn-server-api.publishing-conventions")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.3")
}

tasks.test {
    useJUnitPlatform()
}
