package com.example.lumendepths.client;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.LumenEntities;
import com.example.lumendepths.entity.model.CrystalBeetleModel;
import com.example.lumendepths.entity.model.CrystalSnailEntityModel;
import com.example.lumendepths.entity.model.GloomMawModel;
import com.example.lumendepths.entity.model.GlowmothEntityModel;
import com.example.lumendepths.entity.model.LumenSpriteModel;
import com.example.lumendepths.entity.model.ShardWraithModel;
import com.example.lumendepths.entity.renderer.CrystalBeetleRenderer;
import com.example.lumendepths.entity.renderer.CrystalSnailEntityRenderer;
import com.example.lumendepths.entity.renderer.GloomMawRenderer;
import com.example.lumendepths.entity.renderer.GlowmothEntityRenderer;
import com.example.lumendepths.entity.renderer.LumenSpriteRenderer;
import com.example.lumendepths.entity.renderer.ShardWraithRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class LumenDepthsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(GlowmothEntityModel.LAYER, GlowmothEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(CrystalSnailEntityModel.LAYER, CrystalSnailEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(LumenSpriteModel.LAYER, LumenSpriteModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(CrystalBeetleModel.LAYER, CrystalBeetleModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(ShardWraithModel.LAYER, ShardWraithModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(GloomMawModel.LAYER, GloomMawModel::getTexturedModelData);

		EntityRendererRegistry.register(LumenEntities.GLOWMOTH, GlowmothEntityRenderer::new);
		EntityRendererRegistry.register(LumenEntities.CRYSTAL_SNAIL, CrystalSnailEntityRenderer::new);
		EntityRendererRegistry.register(LumenEntities.LUMEN_SPRITE, LumenSpriteRenderer::new);
		EntityRendererRegistry.register(LumenEntities.CRYSTAL_BEETLE, CrystalBeetleRenderer::new);
		EntityRendererRegistry.register(LumenEntities.SHARD_WRAITH, ShardWraithRenderer::new);
		EntityRendererRegistry.register(LumenEntities.GLOOM_MAW, GloomMawRenderer::new);

		LumenDepthsMod.LOGGER.info("[Lumen Depths] Client init: 6 model layers + 6 renderers registered.");
	}
}
