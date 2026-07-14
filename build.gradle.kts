plugins {
    kotlin("jvm") version "1.9.24"
    application
}

repositories {
    mavenCentral()
}

val jmeVersion = "3.6.1-stable"

dependencies {
    // jMonkeyEngine core + desktop + LWJGL3 renderer
    implementation("org.jmonkeyengine:jme3-core:$jmeVersion")
    implementation("org.jmonkeyengine:jme3-desktop:$jmeVersion")
    implementation("org.jmonkeyengine:jme3-lwjgl3:$jmeVersion")

    // Post-processing effects (bloom, SSAO, etc.) — optional but useful
    implementation("org.jmonkeyengine:jme3-effects:$jmeVersion")

    // Bullet physics for collision, traps, rigid bodies
    implementation("org.jmonkeyengine:jme3-bullet:$jmeVersion")
    implementation("org.jmonkeyengine:jme3-bullet-native:$jmeVersion")

    // Asset loading helpers (texture atlases, model loaders)
    implementation("org.jmonkeyengine:jme3-plugins:$jmeVersion")
}

application {
    mainClass.set("com.example.dungeon.MainKt")
    // Required so LWJGL can load native libs from the JVM
    applicationDefaultJvmArgs = listOf(
        "--add-opens", "java.base/java.nio=ALL-UNNAMED",
        "--add-opens", "java.base/sun.nio.ch=ALL-UNNAMED"
    )
}

kotlin {
    jvmToolchain(17)
}

// Fat jar task — optional convenience for distribution
tasks.register<Jar>("fatJar") {
    archiveClassifier.set("all")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest { attributes["Main-Class"] = "com.example.dungeon.MainKt" }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get())
}
