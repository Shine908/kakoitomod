package com.example.lumendepths;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LumenItems {

	public static final Item LUMEN_SHARD = simple(64);
	public static final Item WISP_ESSENCE = simple(64);
	public static final Item AURORA_FRUIT = new Item(new Item.Settings()
			.food(new FoodComponent.Builder()
					.nutrition(4)
					.saturationModifier(0.4f)
					.build()));
	public static final Item LUMEN_LANTERN = simple(16);

	private static Item simple(int maxCount) {
		return new Item(new Item.Settings().maxCount(maxCount));
	}

	public static void registerItems() {
		register("lumen_shard", LUMEN_SHARD);
		register("wisp_essence", WISP_ESSENCE);
		register("aurora_fruit", AURORA_FRUIT);
		register("lumen_lantern", LUMEN_LANTERN);
	}

	public static void register(String name, Item item) {
		Registry.register(Registries.ITEM, Identifier.of(LumenDepthsMod.MOD_ID, name), item);
		LumenDepthsMod.LOGGER.info("[Lumen Depths] Registered item: {}", name);
	}
}
