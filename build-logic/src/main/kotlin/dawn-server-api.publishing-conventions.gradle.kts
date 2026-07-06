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
            name = "Artifactory"
            url = if (version.toString().endsWith("SNAPSHOT")) {
                uri("https://repo.feathermc.net/artifactory/maven-snapshots/")
            } else {
                uri("https://repo.feathermc.net/artifactory/maven-releases/")
            }
            credentials {
                username =
                    project.findProperty("artifactory.username") as? String ?: System.getenv("ARTIFACTORY_USERNAME")
                password =
                    project.findProperty("artifactory.password") as? String ?: System.getenv("ARTIFACTORY_PASSWORD")
            }
        }
    }
}
