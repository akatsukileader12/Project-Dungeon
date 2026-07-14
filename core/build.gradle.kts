plugins {
    kotlin("jvm")
}

repositories {
    google()
    mavenCentral()
}

val jmeVersion = "3.6.1-stable"

dependencies {
    // Platform-agnostic engine pieces only. No renderer (jme3-desktop/
    // jme3-lwjgl3/jme3-android) and no Bullet natives here -- those are
    // supplied by whichever platform module (desktop/android) runs this code.
    implementation("org.jmonkeyengine:jme3-core:$jmeVersion")
    implementation("org.jmonkeyengine:jme3-effects:$jmeVersion")

    // Compile-only: gives us the com.jme3.bullet.* API surface (Minie is a
    // drop-in replacement for the discontinued jme3-bullet) without pulling
    // in either platform's native binaries. The "+bare" variant ships zero
    // natives. Each platform module supplies its own real Minie variant
    // (desktop natives vs Android natives) on its runtime classpath.
    compileOnly("com.github.stephengold:Minie:9.0.1+bare")
}

kotlin {
    jvmToolchain(17)
}
