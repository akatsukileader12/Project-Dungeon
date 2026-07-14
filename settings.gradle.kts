plugins {
    // Lets Gradle auto-provision a matching JDK (17) if one isn't already
    // installed locally, instead of failing the build.
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "project-dungeon"
