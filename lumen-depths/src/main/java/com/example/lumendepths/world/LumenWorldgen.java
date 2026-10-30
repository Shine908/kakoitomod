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

    private static final RegistryKey<net.minecraft.world.biome.Biome> LUMEN_CAVES =
            RegistryKey.of(RegistryKeys.BIOME, Identifier.of(LumenDepthsMod.MOD_ID, "lumen_caves"));

    public static void register() {
        // NOTE: no BiomeModifications here. All feature/spawn wiring for the
        // lumen_caves biome lives in its datapack JSON
        // (data/lumen_depths/worldgen/biome/lumen_caves.json). Registering
        // dynamic biome modifications that reference datapack JSON entries
        // caused a hard "Failed to load registries" crash on world creation
        // in 1.21.1, so this class intentionally keeps zero runtime hooks.
        LumenDepthsMod.LOGGER.info("[Lumen Depths] Worldgen is JSON-driven (biome: {})", LUMEN_CAVES.getValue());
    }
}
