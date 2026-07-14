plugins {
    kotlin("jvm")
    application
}

repositories {
    google()
    mavenCentral()
}

val jmeVersion = "3.6.1-stable"

dependencies {
    implementation(project(":core"))

    // Desktop renderer
    implementation("org.jmonkeyengine:jme3-desktop:$jmeVersion")
    implementation("org.jmonkeyengine:jme3-lwjgl3:$jmeVersion")
    implementation("org.jmonkeyengine:jme3-plugins:$jmeVersion")

    // Bullet physics -- desktop-native variant (Linux/Windows/macOS .so/.dll/.dylib)
    implementation("com.github.stephengold:Minie:9.0.1")
}

application {
    mainClass.set("com.example.dungeon.desktop.DesktopLauncherKt")
    // Required so LWJGL can load native libs from the JVM
    applicationDefaultJvmArgs = listOf(
        "--add-opens", "java.base/java.nio=ALL-UNNAMED",
        "--add-opens", "java.base/sun.nio.ch=ALL-UNNAMED"
    )
}

kotlin {
    jvmToolchain(17)
}

// Fat jar task -- self-contained runnable desktop JAR
tasks.register<Jar>("fatJar") {
    archiveClassifier.set("all")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest { attributes["Main-Class"] = "com.example.dungeon.desktop.DesktopLauncherKt" }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get())
}
