package com.example.lumendepths;

import com.example.lumendepths.entity.CrystalBeetleEntity;
import com.example.lumendepths.entity.CrystalSnailEntity;
import com.example.lumendepths.entity.GlowmothEntity;
import com.example.lumendepths.entity.GloomMawEntity;
import com.example.lumendepths.entity.LumenSpriteEntity;
import com.example.lumendepths.entity.ShardWraithEntity;
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
Registries.ENTITY_TYPE, id("glowmoth"),
EntityType.Builder.<GlowmothEntity>create(GlowmothEntity::new, SpawnGroup.AMBIENT)
.dimensions(0.5f, 0.4f).maxTrackingRange(8).build("glowmoth"));

public static final EntityType<CrystalSnailEntity> CRYSTAL_SNAIL = Registry.register(
Registries.ENTITY_TYPE, id("crystal_snail"),
EntityType.Builder.<CrystalSnailEntity>create(CrystalSnailEntity::new, SpawnGroup.CREATURE)
.dimensions(0.8f, 0.6f).maxTrackingRange(8).build("crystal_snail"));

public static final EntityType<LumenSpriteEntity> LUMEN_SPRITE = Registry.register(
Registries.ENTITY_TYPE, id("lumen_sprite"),
EntityType.Builder.<LumenSpriteEntity>create(LumenSpriteEntity::new, SpawnGroup.AMBIENT)
.dimensions(0.4f, 0.4f).maxTrackingRange(8).build("lumen_sprite"));

public static final EntityType<CrystalBeetleEntity> CRYSTAL_BEETLE = Registry.register(
Registries.ENTITY_TYPE, id("crystal_beetle"),
EntityType.Builder.<CrystalBeetleEntity>create(CrystalBeetleEntity::new, SpawnGroup.CREATURE)
.dimensions(0.7f, 0.4f).maxTrackingRange(8).build("crystal_beetle"));

public static final EntityType<ShardWraithEntity> SHARD_WRAITH = Registry.register(
Registries.ENTITY_TYPE, id("shard_wraith"),
EntityType.Builder.<ShardWraithEntity>create(ShardWraithEntity::new, SpawnGroup.MONSTER)
.dimensions(0.7f, 1.6f).maxTrackingRange(8).build("shard_wraith"));

public static final EntityType<GloomMawEntity> GLOOM_MAW = Registry.register(
Registries.ENTITY_TYPE, id("gloom_maw"),
EntityType.Builder.<GloomMawEntity>create(GloomMawEntity::new, SpawnGroup.MONSTER)
.dimensions(0.9f, 0.8f).maxTrackingRange(8).build("gloom_maw"));

// Spawn eggs need a non-null EntityType at construction time in 1.21.1,
// so they are created and registered here, after the entity types above.
public static final Item GLOWMOTH_SPAWN_EGG = registerEgg("glowmoth_spawn_egg", GLOWMOTH, 0x4DEEEA, 0xB39DDB);
public static final Item CRYSTAL_SNAIL_SPAWN_EGG = registerEgg("crystal_snail_spawn_egg", CRYSTAL_SNAIL, 0x7B68EE, 0x87CEEB);
public static final Item LUMEN_SPRITE_SPAWN_EGG = registerEgg("lumen_sprite_spawn_egg", LUMEN_SPRITE, 0xEFFFFF, 0x66E0FF);
public static final Item CRYSTAL_BEETLE_SPAWN_EGG = registerEgg("crystal_beetle_spawn_egg", CRYSTAL_BEETLE, 0x3A5FD0, 0x8A6FE8);
public static final Item SHARD_WRAITH_SPAWN_EGG = registerEgg("shard_wraith_spawn_egg", SHARD_WRAITH, 0x1A1A3E, 0x4DEEEA);
public static final Item GLOOM_MAW_SPAWN_EGG = registerEgg("gloom_maw_spawn_egg", GLOOM_MAW, 0x2B1B3D, 0xD94FFF);

private static <T extends net.minecraft.entity.Entity> Item registerEgg(
String name, EntityType<T> type, int primary, int secondary) {
return Registry.register(Registries.ITEM, id(name),
new SpawnEggItem(type, primary, secondary, new Item.Settings()));
}

private static Identifier id(String path) {
return Identifier.of(LumenDepthsMod.MOD_ID, path);
}

public static void registerEntities() {
LumenDepthsMod.LOGGER.info("[Lumen Depths] Registered 6 entities (+spawn eggs)");
}

public static void registerDefaultAttributes() {
FabricDefaultAttributeRegistry.register(GLOWMOTH, GlowmothEntity.createAttributes().build());
FabricDefaultAttributeRegistry.register(CRYSTAL_SNAIL, CrystalSnailEntity.createAttributes().build());
FabricDefaultAttributeRegistry.register(LUMEN_SPRITE, LumenSpriteEntity.createAttributes().build());
FabricDefaultAttributeRegistry.register(CRYSTAL_BEETLE, CrystalBeetleEntity.createAttributes().build());
FabricDefaultAttributeRegistry.register(SHARD_WRAITH, ShardWraithEntity.createAttributes().build());
FabricDefaultAttributeRegistry.register(GLOOM_MAW, GloomMawEntity.createAttributes().build());
LumenDepthsMod.LOGGER.info("[Lumen Depths] Registered default attributes");
}
}
