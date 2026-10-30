package com.example.lumendepths.entity.renderer;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.entity.LumenSpriteEntity;
import com.example.lumendepths.entity.model.LumenSpriteModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class LumenSpriteRenderer extends MobEntityRenderer<LumenSpriteEntity, LumenSpriteModel> {

	private static final Identifier TEXTURE =
			Identifier.of(LumenDepthsMod.MOD_ID, "textures/entity/lumen_sprite.png");

	public LumenSpriteRenderer(EntityRendererFactory.Context context) {
		super(context, new LumenSpriteModel(context.getPart(LumenSpriteModel.LAYER)), 0.3f);
	}

	@Override
	public Identifier getTexture(LumenSpriteEntity entity) {
		return TEXTURE;
	}
}
