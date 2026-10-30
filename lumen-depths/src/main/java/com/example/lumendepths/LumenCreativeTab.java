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
			.icon(() -> new ItemStack(LumenItems.LUMEN_SHARD))
			.entries((context, entries) -> {
				entries.add(LumenItems.LUMEN_SHARD);
				entries.add(LumenItems.WISP_ESSENCE);
				entries.add(LumenItems.AURORA_FRUIT);
				entries.add(LumenItems.LUMEN_LANTERN);
				entries.add(LumenEntities.GLOWMOTH_SPAWN_EGG);
				entries.add(LumenEntities.CRYSTAL_SNAIL_SPAWN_EGG);
			})
			.build();

	public static void register() {
		Registry.register(Registries.ITEM_GROUP, MAIN_KEY, MAIN);
		LumenDepthsMod.LOGGER.info("[Lumen Depths] Registered creative tab: {}", MAIN_KEY.getValue());
	}
}
