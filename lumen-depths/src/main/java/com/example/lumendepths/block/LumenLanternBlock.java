package com.example.lumendepths.block;

import net.minecraft.block.LanternBlock;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

/**
 * Real lantern block: standing/hanging states, waterlogging, light level 15.
 * Extends the vanilla LanternBlock so all placement/attachment logic is reused.
 */
public class LumenLanternBlock extends LanternBlock {

	public LumenLanternBlock(Settings settings) {
		super(settings.mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
				.strength(3.5f).requiresTool()
				.sounds(BlockSoundGroup.LANTERN)
				.luminance(state -> 15));
	}
}
