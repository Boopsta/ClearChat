plugins {
    kotlin("jvm") version "1.8.20-Beta"
    id("org.sonarqube") version "4.0.0.2929"
}

group = "io.github"
version = "1.0.4"

val kotlinJvmTarget = JavaVersion.toVersion(17)

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigotmc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4") {
        exclude(module = "kotlin-stdlib-common")
        exclude(module = "kotlin-stdlib-jdk8")
    }

    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")

    testImplementation(kotlin("test"))
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "$kotlinJvmTarget"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "$kotlinJvmTarget"
        }
    }
    processResources {
        filesMatching("plugin.yml") {
            expand("version" to project.version)
        }
    }
    test {
        useJUnitPlatform()
    }
    jar {
        val dependencies = configurations.runtimeClasspath.get().map(::zipTree)
        from(dependencies)
        // We may omit them without any troubles or errors because we never use the Kotlin Reflect library in this project.
        exclude(
            "**/*.kotlin_metadata",
            "**/*.kotlin_module",
            "**/*.kotlin_builtins",
            "**/*.pro",
            "**/*.version",
            "**/*.bin",
            "META-INF/maven/**",
            "META-INF/versions/**"
        )
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "128931_ClearChat")
        property("sonar.organization", "128931")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
