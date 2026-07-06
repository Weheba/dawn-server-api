plugins {
    id("dawn-server-api.java-conventions")
    id("dawn-server-api.publishing-conventions")
}

dependencies {
    implementation(project(":api"))
}