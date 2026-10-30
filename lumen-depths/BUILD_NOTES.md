# Lumen Depths — Build Notes

## Used versions (final, verified by a successful build)
| Component        | Version                | Source of truth |
|------------------|------------------------|-----------------|
| Minecraft        | 1.21.1                 | gradle.properties |
| Fabric Loader    | **0.19.5**             | confirmed to exist on maven.fabricmc.net — no fallback needed |
| Fabric API       | 0.116.17+1.21.1        | latest stable `...+1.21.1` release on maven.fabricmc.net |
| Yarn mappings    | 1.21.1+build.3         | gradle.properties |
| Java             | 21 (Eclipse Temurin)   | toolchain / `options.release = 21` |
| Fabric Loom      | 1.10.1                 | plugins block in build.gradle |
| Gradle wrapper   | 8.14.3                 | gradle/wrapper/gradle-wrapper.properties |
| Mod version      | 1.0.0                  | mod_version property |
| Artifact         | `build/libs/lumen-depths-1.0.0.jar` | |

## Deviations from the original spec and why
1. **Gradle 8.14.3 instead of 8.8.** Fabric Loom 1.10.x requires Gradle >= 8.12; using 8.8 fails during
   plugin configuration. 8.14.3 is the newest 8.x line and fully compatible with the requested "8.8 or newer".
2. **Fabric Loader stayed exactly 0.19.5** — it was verified against `https://maven.fabricmc.net/net/fabricmc/fabric-loader/maven-metadata.xml`, so the "nearest stable" fallback was not needed.
3. **Spawn eggs are registered inside `LumenEntities`**, not `LumenItems`. In 1.21.1 the `SpawnEggItem(EntityType, int, int, Settings)` constructor rejects a null entity type, so eggs must be created after their `EntityType`s are registered. All six items still appear in the creative tab and in the item registry under the required IDs (`lumen_depths:glowmoth_spawn_egg`, `lumen_depths:crystal_snail_spawn_egg`).
4. **Yarn 1.21.1 specifics applied:** `GoalSelector.add(int, Goal)` (not `addGoal`), `ModelPart` fields `pitch/yaw/roll/xScale/yScale/zScale` (no `rotateAngleX`/`setScale`), `EntityModel` implements abstract `render(MatrixStack, VertexConsumer, int, int, int)` directly, textures/IDs via `Identifier.of(...)`.
5. Entity attributes registered through `FabricDefaultAttributeRegistry`; creative tab through `FabricItemGroup.builder()` + `Registries.ITEM_GROUP`.

## How to build manually
```bash
cd lumen-depths
JAVA_HOME=/path/to/jdk21 ./gradlew build --stacktrace --no-daemon
# output: build/libs/lumen-depths-1.0.0.jar
```
Requires JDK 21 and internet access for first run (Minecraft/Mojang/Fabric maven downloads; afterwards everything is cached in `.gradle/loom-cache`).

## How to install into a client
1. Install the Fabric profile for Minecraft 1.21.1 (loader >= 0.19.5) via the official installer or Modrinth launcher.
2. Copy into `.minecraft/mods`:
   - `lumen-depths-1.0.0.jar`
   - `fabric-api-0.116.17+1.21.1.jar` (the full Fabric API jar is required)
3. Launch the profile. The "Lumen Depths" creative tab appears with all six items; use spawn eggs or `/summon lumen_depths:glowmoth` / `/summon lumen_depths:crystal_snail`.

## What the mod contains
- Creative tab `itemGroup.lumen_depths.main` ("Lumen Depths"), icon = Lumen Shard.
- Items: `lumen_shard`, `wisp_essence`, `aurora_fruit` (food: 4 nutrition / 0.4 saturation), `lumen_lantern` (max stack 16), `glowmoth_spawn_egg`, `crystal_snail_spawn_egg`.
- Mobs: Glowmoth (AMBIENT, 0.5x0.4, HP 6, hovering flight AI, drops Wisp Essence + chance of Lumen Shard); Crystal Snail (CREATURE, 0.8x0.6, HP 10, slow passive animal, breeds on sugar, tempted by sugar/aurora fruit, drops Lumen Shard).
- Java models (64x64 TexturedModelData), MobEntityRenderers, model layers registered client-side.
- Procedurally generated original PNG textures (items 16x16, entities 64x64, icon 128x128) produced by `tools/generate_assets.py` (Pillow). No third-party assets used.
- Localization: en_us + ru_ru.

## Known limitations
- Natural biome spawning is intentionally not configured; mobs spawn via spawn eggs and `/summon` only.
- Entity textures are simple procedural placeholders aligned to the model UV layout; visuals are basic but distinct.
- Sounds reuse vanilla sound events (parrot/generic/slime/turtle) — no custom sound files.
- `lumen_lantern` is an item-only decoration as specified (no block registration).
