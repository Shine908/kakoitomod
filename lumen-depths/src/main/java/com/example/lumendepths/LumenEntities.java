package com.example.lumendepths;

import com.example.lumendepths.entity.CrystalSnailEntity;
import com.example.lumendepths.entity.GlowmothEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LumenEntities {

	public static final EntityType<GlowmothEntity> GLOWMOTH = Registry.register(
			Registries.ENTITY_TYPE,
			id("glowmoth"),
			EntityType.Builder.<GlowmothEntity>create(GlowmothEntity::new, SpawnGroup.AMBIENT)
					.dimensions(0.5f, 0.4f)
					.maxTrackingRange(8)
					.build("glowmoth"));

	public static final EntityType<CrystalSnailEntity> CRYSTAL_SNAIL = Registry.register(
			Registries.ENTITY_TYPE,
			id("crystal_snail"),
			EntityType.Builder.<CrystalSnailEntity>create(CrystalSnailEntity::new, SpawnGroup.CREATURE)
					.dimensions(0.8f, 0.6f)
					.maxTrackingRange(8)
					.build("crystal_snail"));

	// Spawn eggs need a non-null EntityType at construction time in 1.21.1,
	// so they are created and registered here, after the entity types above.
	public static final Item GLOWMOTH_SPAWN_EGG = Registry.register(
			Registries.ITEM,
			id("glowmoth_spawn_egg"),
			new SpawnEggItem(GLOWMOTH, 0x4DEEEA, 0xB39DDB, new Item.Settings()));

	public static final Item CRYSTAL_SNAIL_SPAWN_EGG = Registry.register(
			Registries.ITEM,
			id("crystal_snail_spawn_egg"),
			new SpawnEggItem(CRYSTAL_SNAIL, 0x7B68EE, 0x87CEEB, new Item.Settings()));

	private static Identifier id(String path) {
		return Identifier.of(LumenDepthsMod.MOD_ID, path);
	}

	public static void registerEntities() {
		// Static initializers above perform the actual registration when this
		// class is first touched from the mod initializer.
		LumenDepthsMod.LOGGER.info("[Lumen Depths] Registered entities: glowmoth, crystal_snail (+spawn eggs)");
	}

	public static void registerDefaultAttributes() {
		FabricDefaultAttributeRegistry.register(GLOWMOTH, GlowmothEntity.createAttributes().build());
		FabricDefaultAttributeRegistry.register(CRYSTAL_SNAIL, CrystalSnailEntity.createAttributes().build());
		LumenDepthsMod.LOGGER.info("[Lumen Depths] Registered default attributes");
	}
}
