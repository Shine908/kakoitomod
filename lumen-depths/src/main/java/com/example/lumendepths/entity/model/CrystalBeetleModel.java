package com.example.lumendepths.entity.model;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.entity.CrystalBeetleEntity;
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
 * Low, wide beetle with a segmented glowing shell and tiny legs.
 */
public class CrystalBeetleModel extends EntityModel<CrystalBeetleEntity> {

	public static final EntityModelLayer LAYER =
			new EntityModelLayer(Identifier.of(LumenDepthsMod.MOD_ID, "crystal_beetle"), "main");

	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart legL;
	private final ModelPart legR;

	public CrystalBeetleModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.legL = root.getChild("legs_l");
		this.legR = root.getChild("legs_r");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		root.addChild("body", ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(-4.0f, -3.0f, -5.0f, 8.0f, 4.0f, 10.0f, Dilation.NONE)
						.uv(0, 14)
						.cuboid(-2.5f, -4.5f, -3.0f, 5.0f, 2.0f, 6.0f, Dilation.NONE),
				ModelTransform.pivot(0.0f, 19.0f, 0.0f));

		root.addChild("head", ModelPartBuilder.create()
						.uv(26, 0)
						.cuboid(-2.5f, -2.0f, -3.0f, 5.0f, 4.0f, 3.0f, Dilation.NONE)
						.uv(26, 10)
						.cuboid(-3.5f, -2.5f, -2.0f, 1.0f, 1.0f, 2.0f, Dilation.NONE)
						.uv(32, 10)
						.cuboid(2.5f, -2.5f, -2.0f, 1.0f, 1.0f, 2.0f, Dilation.NONE),
				ModelTransform.pivot(0.0f, 19.0f, -5.0f));

		root.addChild("legs_l", ModelPartBuilder.create()
						.uv(40, 0)
						.cuboid(0.0f, 0.0f, -4.0f, 1.0f, 3.0f, 1.0f, Dilation.NONE)
						.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 3.0f, 1.0f, Dilation.NONE)
						.cuboid(0.0f, 0.0f, 3.0f, 1.0f, 3.0f, 1.0f, Dilation.NONE),
				ModelTransform.pivot(4.0f, 20.0f, 0.0f));

		root.addChild("legs_r", ModelPartBuilder.create()
						.uv(40, 8)
						.mirrored().cuboid(-1.0f, 0.0f, -4.0f, 1.0f, 3.0f, 1.0f, Dilation.NONE)
						.cuboid(-1.0f, 0.0f, 0.0f, 1.0f, 3.0f, 1.0f, Dilation.NONE)
						.cuboid(-1.0f, 0.0f, 3.0f, 1.0f, 3.0f, 1.0f, Dilation.NONE),
				ModelTransform.pivot(-4.0f, 20.0f, 0.0f));

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(CrystalBeetleEntity entity, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yaw = netHeadYaw * MathHelper.RADIANS_PER_DEGREE;
		this.head.pitch = headPitch * MathHelper.RADIANS_PER_DEGREE;
		float swing = MathHelper.cos(limbSwing * 0.6662f) * 0.4f * limbSwingAmount;
		this.legL.roll = swing;
		this.legR.roll = -swing;
	}


	@Override
	public void render(net.minecraft.client.util.math.MatrixStack matrixStack, net.minecraft.client.render.VertexConsumer vertexConsumer, int light, int overlay, int color) {
		this.body.render(matrixStack, vertexConsumer, light, overlay, color);
		this.head.render(matrixStack, vertexConsumer, light, overlay, color);
		this.legL.render(matrixStack, vertexConsumer, light, overlay, color);
		this.legR.render(matrixStack, vertexConsumer, light, overlay, color);
	}
}
