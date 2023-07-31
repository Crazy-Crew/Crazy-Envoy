plugins {
    id("paper-plugin")
    id("publish-task")

    id("xyz.jpenilla.run-paper") version "2.1.0"
}

repositories {
    flatDir {
        dirs("libs")
    }
}

dependencies {
    compileOnly(libs.placeholder.api)
    compileOnly(libs.itemsadder.api)

    compileOnly("cmi-api:CMI-API")
    compileOnly("cmi-lib:CMILib")

    compileOnly(libs.decent.holograms)
    compileOnly(libs.fancy.holograms)
    compileOnly(libs.fancy.npcs)

    compileOnly(libs.worldguard.api)

    implementation(libs.bstats.bukkit)

    implementation(libs.triumph.cmds)

    implementation(libs.nbt.api)
}

val buildNumber: String? = System.getenv("BUILD_NUMBER")
val buildVersion = "${rootProject.version}-b$buildNumber"

rootProject.version = if (buildNumber != null) buildVersion else rootProject.version

val isSnapshot = rootProject.version.toString().contains("snapshot") || rootProject.version.toString() == buildVersion
val javaComponent: SoftwareComponent = components["java"]

tasks {
    reobfJar {
        val file = File("$rootDir/jars")

        if (!file.exists()) file.mkdirs()

        outputJar.set(layout.buildDirectory.file("$file/${rootProject.name}-${rootProject.version}.jar"))
    }

    runServer {
        minecraftVersion("1.20")
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(
                "name" to rootProject.name,
                "group" to rootProject.group,
                "version" to rootProject.version,
                "description" to rootProject.description,
                "website" to "https://modrinth.com/plugin/${rootProject.name.lowercase()}"
            )
        }
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = rootProject.group.toString()
                artifactId = "${rootProject.name.lowercase()}-api"

                version = rootProject.version.toString()

                from(javaComponent)
            }
        }

        repositories {
            maven {
                credentials {
                    this.username = System.getenv("gradle_username")
                    this.password = System.getenv("gradle_password")
                }

                if (isSnapshot) {
                    url = uri("https://repo.crazycrew.us/snapshots/")
                    return@maven
                }

                url = uri("https://repo.crazycrew.us/releases/")
            }
        }
    }
}