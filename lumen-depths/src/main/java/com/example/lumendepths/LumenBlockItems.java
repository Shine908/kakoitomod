package com.example.lumendepths;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/** Registers BlockItems for every mod block (same IDs as the blocks). */
public class LumenBlockItems {

private static void register(String name, net.minecraft.block.Block block) {
Registry.register(Registries.ITEM, Identifier.of(LumenDepthsMod.MOD_ID, name),
new BlockItem(block, new Item.Settings()));
LumenDepthsMod.LOGGER.info("[Lumen Depths] Registered block item: {}", name);
}

public static void registerBlockItems() {
register("lumen_ore", LumenBlocks.LUMEN_ORE);
register("deepslate_lumen_ore", LumenBlocks.DEEPSLATE_LUMEN_ORE);
register("aurora_ore", LumenBlocks.AURORA_ORE);
register("lumen_crystal_block", LumenBlocks.LUMEN_CRYSTAL_BLOCK);
register("aurora_crystal_block", LumenBlocks.AURORA_CRYSTAL_BLOCK);
register("lumen_crystal_cluster", LumenBlocks.LUMEN_CRYSTAL_CLUSTER);
register("crystal_bricks", LumenBlocks.CRYSTAL_BRICKS);
register("lumen_lantern", LumenBlocks.LUMEN_LANTERN);
}
}
