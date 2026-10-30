# Lumen Depths — Build Notes (v2.0.0)

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
| Mod version      | 2.0.0                  | mod_version property |
| Artifact         | `build/libs/lumen-depths-2.0.0.jar` | |

## Deviations from the original spec and why
1. **Gradle 8.14.3 instead of 8.8.** Fabric Loom 1.10.x requires Gradle >= 8.12; using 8.8 fails during plugin configuration. 8.14.3 satisfies "8.8 or newer".
2. **Fabric Loader stayed exactly 0.19.5** — verified against maven.fabricmc.net, so the "nearest stable" fallback was not needed.
3. **Spawn eggs are registered inside `LumenEntities`**, not `LumenItems`. In 1.21.1 the `SpawnEggItem(EntityType, int, int, Settings)` constructor rejects a null entity type, so eggs must be created after their `EntityType`s. All eggs keep the required IDs and appear in the creative tab.
4. **Yarn 1.21.1 specifics applied:** `GoalSelector.add(int, Goal)` (not `addGoal`), `ModelPart` fields `pitch/yaw/roll/xScale/yScale/zScale`, textures/IDs via `Identifier.of(...)`, loot tables under `data/<ns>/loot_table/` and recipes under `data/<ns>/recipe/` (singular folders in 1.21.1).
5. Data files (recipes, loot tables, tags) are produced by an in-mod **datagen** entrypoint (`fabric-datagen`) and committed as static JSON resources so the jar is self-contained.

## What was fixed in 2.0.0
- **Lumen Lantern is now a real block** (`block/LumenLanternBlock` extends vanilla `LanternBlock`): places on floor, hangs under ceilings (`hanging=true/false` blockstate), waterloggable, light level 15, pickaxe-minable, drops itself. `/give @p lumen_depths:lumen_lantern` gives a placeable glowing lantern. Registered in `Registries.BLOCK` + BlockItem with the same ID `lumen_lantern` (old fake item replaced; registry ID preserved).
- **Crystal Snail texture/model rebuilt**: simplified TexturedModelData (body base + shell + antennae), strict 64x64 UV layout inside a freshly generated 64x64 RGBA PNG; renderer returns `lumen_depths:textures/entity/crystal_snail.png`.
- **Glowmoth actually flies**: no-gravity ambient mob with custom `FlyAroundGoal` (random cave waypoints, visible speed), `SwimGoal`, `LookAroundGoal`; attributes MAX_HEALTH 6, MOVEMENT_SPEED 0.2, FLYING_SPEED 0.6.

## What was added in 2.0.0
- **Biome `lumen_caves` ("Lumen Caves" / "Светозарные пещеры")**: JSON worldgen biome (temperature 0.5, no precipitation, teal-violet fog, glowing cold water colors), spawns AMBIENT (glowmoth, lumen_sprite), CREATURE (crystal_snail, crystal_beetle), MONSTER (shard_wraith, gloom_maw). Ore/decor features placed across overworld generation via JSON placed features + `BiomeModifications`.
- **Blocks (8)**: `lumen_ore`, `deepslate_lumen_ore`, `aurora_ore`, `lumen_crystal_block` (light 12), `aurora_crystal_block` (light 15), `lumen_crystal_cluster` (cutout model, light 8), `crystal_bricks`, `lumen_lantern`. Each has block model, item model, blockstate, texture, loot table, pickaxe/needs_stone_tool tags entries.
- **Items**: `aurora_gem`, `crystal_dust`, `gloom_fang` (+ models, textures, localization, creative tab).
- **Mobs**: `lumen_sprite` (passive flyer, drops wisp_essence), `crystal_beetle` (passive ground, flees player, drops crystal_dust + chance lumen_shard), `shard_wraith` (flying hostile, HP 16, contact damage ~3, drops shards/essence/chance gem), `gloom_maw` (ground hostile, HP 20, melee 4, speed ~0.28, drops lumen_shard + gloom_fang). All with EntityType, attributes, Java models, 64x64 procedural textures, renderers, spawn eggs, loot tables.
- **Recipes**: 4 lumen_shard -> lumen_crystal_block; 4 aurora_gem -> aurora_crystal_block; crystal_bricks crafting; lumen_shard + iron ingot -> lumen_lantern; gem/shard unpacking recipes; smelting ore->shard/gem (with silk-touch alternative on the block loot tables).
- **Creative tab updated**: all old items, new items, all blocks, all 6 spawn eggs. Icon remains Lumen Shard.
- **Localization**: en_us + ru_ru cover every item, block, entity, spawn egg and the biome name.

## How to build manually
```bash
cd lumen-depths
JAVA_HOME=/path/to/jdk21 ./gradlew build --stacktrace --no-daemon
# output: build/libs/lumen-depths-2.0.0.jar
```
Requires JDK 21 and internet on first run (Minecraft/Mojang/Fabric downloads; afterwards cached in `.gradle/loom-cache`).

## How to install into a client
1. Install a Fabric profile for Minecraft 1.21.1 (loader >= 0.19.5).
2. Copy into `.minecraft/mods`: `lumen-depths-2.0.0.jar` and `fabric-api-0.116.17+1.21.1.jar`.
3. Launch. The "Lumen Depths" creative tab contains everything.

## How to verify the fixes
- **Lantern**: `/give @p lumen_depths:lumen_lantern` — place on floor (standing) or under a block (hanging); emits light 15; breaks with pickaxe; drops itself.
- **Snail texture**: `/summon lumen_depths:crystal_snail ~ ~ ~` — violet body, blue crystal shell, no missing-texture artifacts.
- **Flight**: `/summon lumen_depths:glowmoth` — moves actively along random waypoints, does not hover frozen.
- **Biome**: create/load a world, then `/locate biome lumen_depths:lumen_caves`. To paint it manually use the correct 1.21.1 command (note: it is `/fillbiome`, singular):
  ```
  /fillbiome ~ ~ ~ ~16 ~16 ~16 lumen_depths:lumen_caves
  ```

## Summon commands (all mobs)
```
/summon lumen_depths:glowmoth
/summon lumen_depths:crystal_snail
/summon lumen_depths:lumen_sprite
/summon lumen_depths:crystal_beetle
/summon lumen_depths:shard_wraith
/summon lumen_depths:gloom_maw
```

## Known limitations
- Biome generation: ore/decor features and mob spawns are injected into overworld biomes via Fabric `BiomeModifications` + JSON placements; the full underground-carver replacement of vanilla cave biomes (cave_settings/biome_source JSON mixing) is fragile across world types, so `lumen_caves` may be rare or absent in existing chunks. Use `/locate biome` / `/fillbiome` to inspect it; new worlds get the features through the datapack shipped in the jar.
- Entity/block textures are procedurally generated placeholders (original art, no third-party assets).
- Sounds reuse vanilla sound events; no custom sound files.
- `lumen_crystal_cluster` is a simple floor-placed cutout block (wall/ceiling multi-placement omitted for stability).
- Natural mob spawning relies on BiomeModifications weight tuning; exact spawn rates may need balancing per playtest.

## Hotfix 2.0.0 (registry load crash)
- Исправлен краш "Failed to load registries": в configured_feature ошибочный `minecraft:block_matcher` → `minecraft:block_match`; в lumen_crystal_cluster_patch `minecraft:single_provider` → `minecraft:simple_state_provider` (+ inline state); из биома удалён несуществующий карвер `minecraft:cave_z` (остались cave, canyon). Все JSON в JAR провалидированы программно.
