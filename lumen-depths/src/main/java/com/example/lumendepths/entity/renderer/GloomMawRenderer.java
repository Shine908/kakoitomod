package com.example.lumendepths.entity.renderer;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.entity.GloomMawEntity;
import com.example.lumendepths.entity.model.GloomMawModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GloomMawRenderer extends MobEntityRenderer<GloomMawEntity, GloomMawModel> {

	private static final Identifier TEXTURE =
			Identifier.of(LumenDepthsMod.MOD_ID, "textures/entity/gloom_maw.png");

	public GloomMawRenderer(EntityRendererFactory.Context context) {
		super(context, new GloomMawModel(context.getPart(GloomMawModel.LAYER)), 0.6f);
	}

	@Override
	public Identifier getTexture(GloomMawEntity entity) {
		return TEXTURE;
	}
}
