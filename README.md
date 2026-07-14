# Project Dungeon

A **jMonkeyEngine + Kotlin** dungeon RPG starter — isometric/MOBA-style top-down camera, click-to-move navigation, Bullet physics, and a simple box-room dungeon to build from.

Runs on both **desktop** (Windows/macOS/Linux via LWJGL3) and **Android** (via jME's `AndroidHarness` + OpenGL ES) from the same game-logic code.

## Stack

| Layer | Tech |
|-------|------|
| Engine | [jMonkeyEngine 3.6](https://jmonkeyengine.org/) |
| Language | Kotlin 1.9 (JVM 17) |
| Build | Gradle (Kotlin DSL), multi-module |
| Physics | [Minie](https://github.com/stephengold/Minie) (maintained drop-in replacement for the discontinued `jme3-bullet`) |
| Desktop renderer | LWJGL3 |
| Android renderer | jME `jme3-android` (OpenGL ES via `AndroidHarness`) |

## Module layout

```
core/      Engine-agnostic game logic (DungeonGame.kt). No renderer, no
           platform-specific natives. Both platforms run this unmodified.
desktop/   LWJGL3 launcher + fatJar packaging task.
android/   Android application module: AndroidManifest, MainActivity
           (extends AndroidHarness, loads DungeonGame by reflection).
```

## Running on desktop

```bash
# Requires JDK 17+
./gradlew :desktop:run
```

Or produce a self-contained runnable JAR:

```bash
./gradlew :desktop:fatJar
# -> desktop/build/libs/desktop-all.jar
java -jar desktop/build/libs/desktop-all.jar
```

## Running on Android

Requires the Android SDK (Android Studio, or `sdkmanager` with `platforms;android-34` + `build-tools;34.0.0`).

```bash
./gradlew :android:assembleDebug
# -> android/build/outputs/apk/debug/android-debug.apk
```

Install on a device/emulator with `adb install android/build/outputs/apk/debug/android-debug.apk`, or open the project in Android Studio and run the `android` module. Minimum supported OS: Android 8.0 (API 26). Touch input drives the same click-to-move logic as a desktop mouse click.

CI (`.github/workflows/build.yml`) builds both the desktop JAR and the Android debug APK on every push and uploads them as downloadable artifacts from the Actions tab.

## What's in the starter

- **Fixed isometric camera** that follows the player — no rotation, MLBB/Diablo style
- **Click-to-move** player (red box stand-in): raycast from cursor/touch → ground, walk toward point
- **Box-room dungeon** with four walls and static Bullet (Minie) physics collision
- **Lighting**: directional sun + low ambient + warm torch point light

## Natural next steps

- [ ] Swap the box player for a loaded `.glb`/`.gltf` character model (`assetManager.loadModel`)
- [ ] Replace box walls with Kenney dungeon tile meshes
- [ ] Add a navmesh or A* grid so the player routes around obstacles
- [ ] Enemy state machine: `IDLE → CHASE → ATTACK`
- [ ] Inventory system (plain Kotlin data classes, decoupled from rendering)
- [ ] Torchlight flicker animation (`tpf`-driven `PointLight.radius` oscillation)
- [ ] Room transitions / multiple dungeon levels
- [ ] On-screen virtual joystick/buttons for Android (currently relies on touch = click-to-move, which works but a HUD would feel better)

## Free asset sources

| Source | Type | License |
|--------|------|---------|
| [Kenney.nl](https://kenney.nl) | Dungeon/RPG tile packs, characters, props | CC0 |
| [Quaternius](https://quaternius.com) | Low-poly characters & monsters | CC0 |
| [Poly Haven](https://polyhaven.com) | PBR stone/wood/floor textures | CC0 |
| [ambientCG](https://ambientcg.com) | PBR material library | CC0 |
