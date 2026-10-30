package com.example.lumendepths.entity.renderer;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.entity.CrystalSnailEntity;
import com.example.lumendepths.entity.model.CrystalSnailEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class CrystalSnailEntityRenderer extends MobEntityRenderer<CrystalSnailEntity, CrystalSnailEntityModel> {

        private static final Identifier TEXTURE =
                        Identifier.of(LumenDepthsMod.MOD_ID, "textures/entity/crystal_snail.png");

        public CrystalSnailEntityRenderer(EntityRendererFactory.Context context) {
                super(context, new CrystalSnailEntityModel(context.getPart(CrystalSnailEntityModel.LAYER)), 0.4f);
        }

        @Override
        public Identifier getTexture(CrystalSnailEntity entity) {
                return TEXTURE;
        }
}
