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
            name = "DawnNexus"
            url = if (version.toString().endsWith("SNAPSHOT")) {
                uri("https://nexus.dawn.gg/repository/maven-snapshots/")
            } else {
                uri("https://nexus.dawn.gg/repository/maven-releases/")
            }
            credentials {
                username =
                    project.findProperty("dawnNexus.username") as? String ?: System.getenv("NEXUS_MAVEN_USERNAME")
                password =
                    project.findProperty("dawnNexus.password") as? String ?: System.getenv("NEXUS_MAVEN_PASSWORD")
            }
        }
    }
}
