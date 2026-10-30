package com.example.lumendepths;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class LumenCreativeTab {

public static final RegistryKey<ItemGroup> MAIN_KEY =
RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(LumenDepthsMod.MOD_ID, "main"));

private static final ItemGroup MAIN = FabricItemGroup.builder()
.displayName(Text.translatable("itemGroup.lumen_depths.main"))
.icon(() -> new ItemStack(LumenBlocks.LUMEN_CRYSTAL_BLOCK))
.entries((context, entries) -> {
// Resources & food
entries.add(LumenItems.LUMEN_SHARD);
entries.add(LumenItems.WISP_ESSENCE);
entries.add(LumenItems.AURORA_FRUIT);
entries.add(LumenItems.AURORA_GEM);
entries.add(LumenItems.CRYSTAL_DUST);
entries.add(LumenItems.GLOOM_FANG);
// Blocks
entries.add(LumenBlocks.LUMEN_ORE);
entries.add(LumenBlocks.DEEPSLATE_LUMEN_ORE);
entries.add(LumenBlocks.AURORA_ORE);
entries.add(LumenBlocks.LUMEN_CRYSTAL_BLOCK);
entries.add(LumenBlocks.AURORA_CRYSTAL_BLOCK);
entries.add(LumenBlocks.LUMEN_CRYSTAL_CLUSTER);
entries.add(LumenBlocks.CRYSTAL_BRICKS);
entries.add(LumenBlocks.LUMEN_LANTERN);
// Spawn eggs
entries.add(LumenEntities.GLOWMOTH_SPAWN_EGG);
entries.add(LumenEntities.CRYSTAL_SNAIL_SPAWN_EGG);
entries.add(LumenEntities.LUMEN_SPRITE_SPAWN_EGG);
entries.add(LumenEntities.CRYSTAL_BEETLE_SPAWN_EGG);
entries.add(LumenEntities.SHARD_WRAITH_SPAWN_EGG);
entries.add(LumenEntities.GLOOM_MAW_SPAWN_EGG);
})
.build();

public static void register() {
Registry.register(Registries.ITEM_GROUP, MAIN_KEY, MAIN);
LumenDepthsMod.LOGGER.info("[Lumen Depths] Registered creative tab: {}", MAIN_KEY.getValue());
}
}
