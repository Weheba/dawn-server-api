plugins {
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                licenses {
                    license {
                        name.set("Apache-2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "dawn"
            url = if (version.toString().endsWith("SNAPSHOT")) {
                uri("https://nexus.dawn.gg/repository/maven-snapshots/")
            } else {
                uri("https://nexus.dawn.gg/repository/maven-releases/")
            }
            credentials {
                username =
                    project.findProperty("dawnMavenUsername") as? String ?: System.getenv("DAWN_MAVEN_USERNAME")
                password =
                    project.findProperty("dawnMavenPassword") as? String ?: System.getenv("DAWN_MAVEN_PASSWORD")
            }
        }
    }
}
