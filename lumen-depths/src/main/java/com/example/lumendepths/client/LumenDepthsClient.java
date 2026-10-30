package com.example.lumendepths.client;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.LumenEntities;
import com.example.lumendepths.entity.model.CrystalSnailEntityModel;
import com.example.lumendepths.entity.model.GlowmothEntityModel;
import com.example.lumendepths.entity.renderer.CrystalSnailEntityRenderer;
import com.example.lumendepths.entity.renderer.GlowmothEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class LumenDepthsClient implements ClientModInitializer {

        public static final EntityModelLayer GLOWMOTH_LAYER = GlowmothEntityModel.LAYER;
        public static final EntityModelLayer CRYSTAL_SNAIL_LAYER = CrystalSnailEntityModel.LAYER;

        @Override
        public void onInitializeClient() {
                EntityModelLayerRegistry.registerModelLayer(GLOWMOTH_LAYER, GlowmothEntityModel::getTexturedModelData);
                EntityModelLayerRegistry.registerModelLayer(CRYSTAL_SNAIL_LAYER, CrystalSnailEntityModel::getTexturedModelData);

                EntityRendererRegistry.register(LumenEntities.GLOWMOTH, GlowmothEntityRenderer::new);
                EntityRendererRegistry.register(LumenEntities.CRYSTAL_SNAIL, CrystalSnailEntityRenderer::new);

                LumenDepthsMod.LOGGER.info("[Lumen Depths] Client init: model layers + renderers registered.");
        }
}
