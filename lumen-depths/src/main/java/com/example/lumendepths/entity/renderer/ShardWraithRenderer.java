package com.example.lumendepths.entity.renderer;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.entity.ShardWraithEntity;
import com.example.lumendepths.entity.model.ShardWraithModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ShardWraithRenderer extends MobEntityRenderer<ShardWraithEntity, ShardWraithModel> {

	private static final Identifier TEXTURE =
			Identifier.of(LumenDepthsMod.MOD_ID, "textures/entity/shard_wraith.png");

	public ShardWraithRenderer(EntityRendererFactory.Context context) {
		super(context, new ShardWraithModel(context.getPart(ShardWraithModel.LAYER)), 0.5f);
	}

	@Override
	public Identifier getTexture(ShardWraithEntity entity) {
		return TEXTURE;
	}
}
