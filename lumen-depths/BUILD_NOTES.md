# Lumen Depths — Build Notes (v2.1.1)

## Versions
- Minecraft: 1.21.1
- Fabric Loader: 0.19.5
- Fabric API: 0.116.17+1.21.1
- Yarn mappings: 1.21.1+build.3
- Java: 21 (Temurin)
- Fabric Loom: 1.10.1, Gradle wrapper: 8.14.3 (Loom 1.10.x requires Gradle >= 8.12; 8.8 is NOT compatible)

## Changelog
### v2.1.1 (hotfix — client crash "Failed to load registries")
- Removed ALL BiomeModifications runtime hooks (features + mob spawns). Dynamic biome
  modifications that referenced datapack-JSON placed features caused a hard registry
  bootstrap failure on world creation in 1.21.1. Worldgen is now 100% JSON-driven.
- Replaced broken ore configured features (they used the `minecraft:rule_test` key
  `block_matcher`, which does not exist): new `stone_lumen_ore` and `deepslate_lumen_ore`
  features use the correct `predicate_type: minecraft:block_match` inside the ore feature
  target format.
- Added missing targets so ores actually replace stone/deepslate/tuff/granite/andesite/diorite.
- `lumen_crystal_cluster_patch`: added valid `placing_condition` (matching_blocks air, offset -1y).
- Updated lang files for renamed features (en_us, ru_ru).

### v2.1.0
- Version bump of v2.0.0 hotfix (worldgen keys block_match / simple_state_provider / cave carvers fixed).

### v2.0.0
- lumen_lantern converted from item to real block (extends LanternBlock: standing/hanging, waterloggable, light 15).
- Crystal Snail model & texture rewritten (simple cuboids, UV within 64x64).
- Glowmoth flight fixed (noGravity + custom FlyAroundGoal).
- New biome lumen_caves, 8 blocks, 3 items, 4 mobs (lumen_sprite, crystal_beetle, shard_wraith, gloom_maw), spawn eggs, loot tables, tags, recipes, creative tab update, ru_en localization.

## Known limitations
- The lumen_caves biome does NOT generate automatically in vanilla overworld caves
  (dynamic biome-in-caves replacement via JSON alone is not possible without mixing
  into the vanilla multi-noise biome source). It is fully functional when applied with
  /fillbiome and its ores/spawns work there. Mob spawns for the mod's creatures were
  also removed from other overworld biomes in 2.1.1 to keep the game stable; use spawn eggs.
- Textures are procedurally generated placeholders (tools/generate_assets.py, Pillow).
- Sounds reuse vanilla ones.

## Manual build
```
cd lumen-depths
JAVA_HOME=/path/to/jdk21 ./gradlew build --no-daemon
# artifact: build/libs/lumen-depths-2.1.1.jar
```

## Install
Put `lumen-depths-2.1.1.jar` + `fabric-api-0.116.17+1.21.1.jar` into `.minecraft/mods`,
run a Fabric 1.21.1 profile (Loader >= 0.19.5, Java 21).

## Verification commands (correct 1.21.1 syntax)
```
/give @p lumen_depths:lumen_lantern
/summon lumen_depths:glowmoth
/summon lumen_depths:crystal_snail
/summon lumen_depths:lumen_sprite
/summon lumen_depths:crystal_beetle
/summon lumen_depths:shard_wraith
/summon lumen_depths:gloom_maw
/fillbiome ~-8 ~ ~-8 ~8 ~8 ~8 lumen_depths:lumen_caves
```
Note: `/locate biome` exists but may not find lumen_caves since it does not generate
naturally; apply it with /fillbiome instead.
