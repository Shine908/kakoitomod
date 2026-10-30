package com.example.lumendepths.entity.model;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.entity.LumenSpriteEntity;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

/**
 * Floating crystal orb with two drooping tendrils. 64x64 texture.
 */
public class LumenSpriteModel extends EntityModel<LumenSpriteEntity> {

	public static final EntityModelLayer LAYER =
			new EntityModelLayer(Identifier.of(LumenDepthsMod.MOD_ID, "lumen_sprite"), "main");

	private final ModelPart body;
	private final ModelPart tendrilL;
	private final ModelPart tendrilR;

	public LumenSpriteModel(ModelPart root) {
		this.body = root.getChild("body");
		this.tendrilL = root.getChild("tendril_l");
		this.tendrilR = root.getChild("tendril_r");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		root.addChild("body", ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(-3.0f, -3.0f, -3.0f, 6.0f, 6.0f, 6.0f, Dilation.NONE),
				ModelTransform.pivot(0.0f, 14.0f, 0.0f));

		root.addChild("tendril_l", ModelPartBuilder.create()
						.uv(24, 0)
						.cuboid(-1.0f, 0.0f, -1.0f, 2.0f, 5.0f, 2.0f, Dilation.NONE),
				ModelTransform.pivot(-2.0f, 15.0f, 0.0f));

		root.addChild("tendril_r", ModelPartBuilder.create()
						.uv(24, 8)
						.cuboid(-1.0f, 0.0f, -1.0f, 2.0f, 5.0f, 2.0f, Dilation.NONE),
				ModelTransform.pivot(2.0f, 15.0f, 0.0f));

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(LumenSpriteEntity entity, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch) {
		float sway = MathHelper.sin(ageInTicks * 0.4f) * 0.2f;
		this.tendrilL.pitch = sway;
		this.tendrilR.pitch = -sway;
		this.body.yaw = netHeadYaw * MathHelper.RADIANS_PER_DEGREE;
		this.body.pitch = headPitch * MathHelper.RADIANS_PER_DEGREE;
	}


	@Override
	public void render(net.minecraft.client.util.math.MatrixStack matrixStack, net.minecraft.client.render.VertexConsumer vertexConsumer, int light, int overlay, int color) {
		this.body.render(matrixStack, vertexConsumer, light, overlay, color);
		this.tendrilL.render(matrixStack, vertexConsumer, light, overlay, color);
		this.tendrilR.render(matrixStack, vertexConsumer, light, overlay, color);
	}
}
