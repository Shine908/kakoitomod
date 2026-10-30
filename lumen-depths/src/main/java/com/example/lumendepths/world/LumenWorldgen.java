package com.example.lumendepths.world;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.LumenEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;


/**
 * Registers world generation (ore veins) and mob spawns via Fabric Biome API.
 * All ore/placement/feature types are vanilla-serializable, so the JSON-driven
 * datapack side (lumen_depths:overworld_lumens + biome additions) is equivalent;
 * this Java registration guarantees the content even without datapack loading
 * edge cases. If duplicate features appear, the JSON pack can be removed.
 */
public class LumenWorldgen {

private static OreFeatureConfig ore(net.minecraft.block.Block block, int size) {
return new OreFeatureConfig(
OreFeatureConfig.Target.createTargetOverworld(block), size);
}

public static void register() {
// ---- Ore generation (Java side) ----
var lumenVein = RegistryEntry.of(Feature.ORE, ore(com.example.lumendepths.LumenBlocks.LUMEN_ORE, 9));
var deepslateVein = RegistryEntry.of(Feature.ORE, ore(com.example.lumendepths.LumenBlocks.DEEPSLATE_LUMEN_ORE, 7));
var auroraVein = RegistryEntry.of(Feature.ORE, ore(com.example.lumendepths.LumenBlocks.AURORA_ORE, 6));

BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
GenerationStep.Feature.UNDERGROUND_ORES, lumenVein);
BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
GenerationStep.Feature.UNDERGROUND_ORES, deepslateVein);
BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
GenerationStep.Feature.UNDERGROUND_ORES, auroraVein);

// ---- Mob spawns (Java side) ----
// Ambient glowmoths in all overworld biomes.
BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),
SpawnGroup.AMBIENT, LumenEntities.GLOWMOTH, 8, 2, 4);
// Crystal snails & beetles in plains-like biomes.
BiomeModifications.addSpawn(BiomeSelectors.includeByKey(net.minecraft.world.biome.BiomeKeys.PLAINS,
net.minecraft.world.biome.BiomeKeys.SUNFLOWER_PLAINS),
SpawnGroup.CREATURE, LumenEntities.CRYSTAL_SNAIL, 4, 2, 3);
BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),
SpawnGroup.CREATURE, LumenEntities.CRYSTAL_BEETLE, 4, 2, 3);
// Hostile wraiths & gloom maws everywhere in the overworld.
BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),
SpawnGroup.MONSTER, LumenEntities.SHARD_WRAITH, 5, 1, 2);
BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),
SpawnGroup.MONSTER, LumenEntities.GLOOM_MAW, 6, 1, 3);

LumenDepthsMod.LOGGER.info("[Lumen Depths] World generation hooks registered");
}
}
