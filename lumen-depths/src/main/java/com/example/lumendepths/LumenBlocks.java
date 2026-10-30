package com.example.lumendepths;

import com.example.lumendepths.block.LumenLanternBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LumenBlocks {

private static AbstractBlock.Settings ore(MapColor color) {
return AbstractBlock.Settings.create()
.mapColor(color).strength(3.0f).requiresTool()
.sounds(BlockSoundGroup.STONE);
}

public static final Block LUMEN_ORE = new Block(ore(MapColor.GRAY)
.luminance(state -> 5));
public static final Block DEEPSLATE_LUMEN_ORE = new Block(ore(MapColor.GRAY)
.luminance(state -> 5));
public static final Block AURORA_ORE = new Block(ore(MapColor.BLUE)
.strength(4.0f).luminance(state -> 7));

public static final Block LUMEN_CRYSTAL_BLOCK = new Block(AbstractBlock.Settings.create()
.mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
.strength(4.0f).requiresTool().nonOpaque()
.sounds(BlockSoundGroup.AMETHIST_BLOCK)
.luminance(state -> 12));

public static final Block AURORA_CRYSTAL_BLOCK = new Block(AbstractBlock.Settings.create()
.mapColor(MapColor.TERRACOTTA_PURPLE)
.strength(4.0f).requiresTool().nonOpaque()
.sounds(BlockSoundGroup.AMETHIST_BLOCK)
.luminance(state -> 15));

public static final Block LUMEN_CRYSTAL_CLUSTER = new Block(AbstractBlock.Settings.create()
.mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
.strength(2.0f).requiresTool().nonOpaque()
.sounds(BlockSoundGroup.AMETHIST_BLOCK)
.luminance(state -> 10));

public static final Block CRYSTAL_BRICKS = new Block(AbstractBlock.Settings.create()
.mapColor(MapColor.TERRACOTTA_CYAN)
.strength(5.0f, 6.0f).requiresTool()
.sounds(BlockSoundGroup.STONE));

public static final Block LUMEN_LANTERN = new LumenLanternBlock(
AbstractBlock.Settings.create());

private static Identifier id(String path) {
return Identifier.of(LumenDepthsMod.MOD_ID, path);
}

public static void registerBlocks() {
register("lumen_ore", LUMEN_ORE);
register("deepslate_lumen_ore", DEEPSLATE_LUMEN_ORE);
register("aurora_ore", AURORA_ORE);
register("lumen_crystal_block", LUMEN_CRYSTAL_BLOCK);
register("aurora_crystal_block", AURORA_CRYSTAL_BLOCK);
register("lumen_crystal_cluster", LUMEN_CRYSTAL_CLUSTER);
register("crystal_bricks", CRYSTAL_BRICKS);
register("lumen_lantern", LUMEN_LANTERN);
}

private static void register(String name, Block block) {
Registry.register(Registries.BLOCK, id(name), block);
LumenDepthsMod.LOGGER.info("[Lumen Depths] Registered block: {}", name);
}
}
