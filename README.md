# Project Dungeon

A **jMonkeyEngine + Kotlin** dungeon RPG starter — isometric/MOBA-style top-down camera, click-to-move navigation, Bullet physics, and a simple box-room dungeon to build from.

## Stack

| Layer | Tech |
|-------|------|
| Engine | [jMonkeyEngine 3.6](https://jmonkeyengine.org/) |
| Language | Kotlin 1.9 (JVM 17) |
| Build | Gradle (Kotlin DSL) |
| Physics | Bullet (via `jme3-bullet`) |
| Renderer | LWJGL3 |

## Running

```bash
# Requires JDK 17+
./gradlew run
```

Or import the project into IntelliJ IDEA and run the `DungeonGame` main class.

## What's in the starter

- **Fixed isometric camera** that follows the player — no rotation, MLBB/Diablo style
- **Click-to-move** player (red box stand-in): raycast from cursor → ground, walk toward point
- **Box-room dungeon** with four walls and static Bullet physics collision
- **Lighting**: directional sun + low ambient + warm torch point light
- **`fatJar` task** for a self-contained runnable JAR

## Natural next steps

- [ ] Swap the box player for a loaded `.glb`/`.gltf` character model (`assetManager.loadModel`)
- [ ] Replace box walls with Kenney dungeon tile meshes
- [ ] Add a navmesh or A* grid so the player routes around obstacles
- [ ] Enemy state machine: `IDLE → CHASE → ATTACK`
- [ ] Inventory system (plain Kotlin data classes, decoupled from rendering)
- [ ] Torchlight flicker animation (`tpf`-driven `PointLight.radius` oscillation)
- [ ] Room transitions / multiple dungeon levels

## Free asset sources

| Source | Type | License |
|--------|------|---------|
| [Kenney.nl](https://kenney.nl) | Dungeon/RPG tile packs, characters, props | CC0 |
| [Quaternius](https://quaternius.com) | Low-poly characters & monsters | CC0 |
| [Poly Haven](https://polyhaven.com) | PBR stone/wood/floor textures | CC0 |
| [ambientCG](https://ambientcg.com) | PBR material library | CC0 |
