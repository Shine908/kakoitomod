package com.example.lumendepths.entity.renderer;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.entity.GlowmothEntity;
import com.example.lumendepths.entity.model.GlowmothEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GlowmothEntityRenderer extends MobEntityRenderer<GlowmothEntity, GlowmothEntityModel> {

        private static final Identifier TEXTURE =
                        Identifier.of(LumenDepthsMod.MOD_ID, "textures/entity/glowmoth.png");

        public GlowmothEntityRenderer(EntityRendererFactory.Context context) {
                super(context, new GlowmothEntityModel(context.getPart(GlowmothEntityModel.LAYER)), 0.3f);
        }

        @Override
        public Identifier getTexture(GlowmothEntity entity) {
                return TEXTURE;
        }
}
