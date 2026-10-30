package com.example.lumendepths.entity.renderer;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.entity.CrystalBeetleEntity;
import com.example.lumendepths.entity.model.CrystalBeetleModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class CrystalBeetleRenderer extends MobEntityRenderer<CrystalBeetleEntity, CrystalBeetleModel> {

	private static final Identifier TEXTURE =
			Identifier.of(LumenDepthsMod.MOD_ID, "textures/entity/crystal_beetle.png");

	public CrystalBeetleRenderer(EntityRendererFactory.Context context) {
		super(context, new CrystalBeetleModel(context.getPart(CrystalBeetleModel.LAYER)), 0.4f);
	}

	@Override
	public Identifier getTexture(CrystalBeetleEntity entity) {
		return TEXTURE;
	}
}
