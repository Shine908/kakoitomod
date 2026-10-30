package com.example.lumendepths.entity.model;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.entity.GloomMawEntity;
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
 * Wide low predator with a big jaw that snaps while walking.
 */
public class GloomMawModel extends EntityModel<GloomMawEntity> {

	public static final EntityModelLayer LAYER =
			new EntityModelLayer(Identifier.of(LumenDepthsMod.MOD_ID, "gloom_maw"), "main");

	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart jaw;
	private final ModelPart legL;
	private final ModelPart legR;

	public GloomMawModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.jaw = root.getChild("jaw");
		this.legL = root.getChild("legs_l");
		this.legR = root.getChild("legs_r");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		root.addChild("body", ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(-5.0f, -4.0f, -6.0f, 10.0f, 7.0f, 12.0f, Dilation.NONE),
				ModelTransform.pivot(0.0f, 14.0f, 0.0f));

		root.addChild("head", ModelPartBuilder.create()
						.uv(0, 19)
						.cuboid(-4.0f, -4.0f, -5.0f, 8.0f, 6.0f, 5.0f, Dilation.NONE),
				ModelTransform.pivot(0.0f, 13.0f, -6.0f));

		root.addChild("jaw", ModelPartBuilder.create()
						.uv(22, 22)
						.cuboid(-3.0f, 0.0f, -4.0f, 6.0f, 3.0f, 4.0f, Dilation.NONE),
				ModelTransform.pivot(0.0f, 15.0f, -6.0f));

		root.addChild("legs_l", ModelPartBuilder.create()
						.uv(42, 0)
						.cuboid(0.0f, 0.0f, -4.0f, 2.0f, 6.0f, 2.0f, Dilation.NONE)
						.cuboid(0.0f, 0.0f, 2.0f, 2.0f, 6.0f, 2.0f, Dilation.NONE),
				ModelTransform.pivot(5.0f, 16.0f, 0.0f));

		root.addChild("legs_r", ModelPartBuilder.create()
						.uv(42, 8)
						.mirrored().cuboid(-2.0f, 0.0f, -4.0f, 2.0f, 6.0f, 2.0f, Dilation.NONE)
						.cuboid(-2.0f, 0.0f, 2.0f, 2.0f, 6.0f, 2.0f, Dilation.NONE),
				ModelTransform.pivot(-5.0f, 16.0f, 0.0f));

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(GloomMawEntity entity, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yaw = netHeadYaw * MathHelper.RADIANS_PER_DEGREE;
		this.head.pitch = headPitch * MathHelper.RADIANS_PER_DEGREE;
		this.jaw.pitch = MathHelper.abs(MathHelper.sin(ageInTicks * 0.5f)) * 0.35f;
		float swing = MathHelper.cos(limbSwing * 0.6662f) * 0.5f * limbSwingAmount;
		this.legL.roll = swing;
		this.legR.roll = -swing;
	}
}
