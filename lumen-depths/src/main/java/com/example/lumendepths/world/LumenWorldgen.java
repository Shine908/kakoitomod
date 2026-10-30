package com.example.lumendepths.world;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.LumenEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

/**
 * World generation wiring. The actual ore feature configs live in datapack
 * JSON (data/lumen_depths/worldgen/configured_feature/*.json and
 * placed_feature/*.json); here we reference them by key through the Fabric
 * Biome API and add mob spawn entries in code.
 */
public class LumenWorldgen {

    private static RegistryKey<PlacedFeature> placed(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                Identifier.of(LumenDepthsMod.MOD_ID, name));
    }

    public static void register() {
        // ---- Ore veins from JSON placed features ----
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, placed("lumen_ore_upper"));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, placed("lumen_ore_lower"));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, placed("aurora_ore_deep"));

        // ---- Mob spawn additions ----
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(
                        BiomeKeys.DARK_FOREST, BiomeKeys.SPARSE_JUNGLE, BiomeKeys.JUNGLE),
                SpawnGroup.AMBIENT, LumenEntities.GLOWMOTH, 10, 2, 4);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),
                SpawnGroup.AMBIENT, LumenEntities.LUMEN_SPRITE, 6, 1, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(
                        BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.MEADOW),
                SpawnGroup.CREATURE, LumenEntities.CRYSTAL_SNAIL, 4, 2, 3);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),
                SpawnGroup.CREATURE, LumenEntities.CRYSTAL_BEETLE, 5, 2, 3);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),
                SpawnGroup.MONSTER, LumenEntities.SHARD_WRAITH, 4, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),
                SpawnGroup.MONSTER, LumenEntities.GLOOM_MAW, 6, 1, 3);

        LumenDepthsMod.LOGGER.info("[Lumen Depths] World generation hooks registered");
    }
}
