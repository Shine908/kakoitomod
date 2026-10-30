package com.example.lumendepths.entity.model;

import com.example.lumendepths.LumenDepthsMod;
import com.example.lumendepths.entity.ShardWraithEntity;
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
 * Floating hooded ghost made of shards: torso, head, two dangling arms and
 * orbiting shard fins.
 */
public class ShardWraithModel extends EntityModel<ShardWraithEntity> {

	public static final EntityModelLayer LAYER =
			new EntityModelLayer(Identifier.of(LumenDepthsMod.MOD_ID, "shard_wraith"), "main");

	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart armL;
	private final ModelPart armR;
	private final ModelPart shards;

	public ShardWraithModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.armL = root.getChild("arm_l");
		this.armR = root.getChild("arm_r");
		this.shards = root.getChild("shards");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		root.addChild("body", ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(-4.0f, 0.0f, -2.5f, 8.0f, 12.0f, 5.0f, Dilation.NONE),
				ModelTransform.pivot(0.0f, 8.0f, 0.0f));

		root.addChild("head", ModelPartBuilder.create()
						.uv(0, 17)
						.cuboid(-4.0f, -6.0f, -4.0f, 8.0f, 8.0f, 8.0f, Dilation.NONE),
				ModelTransform.pivot(0.0f, 8.0f, 0.0f));

		root.addChild("arm_l", ModelPartBuilder.create()
						.uv(24, 17)
						.cuboid(0.0f, 0.0f, -2.0f, 3.0f, 10.0f, 3.0f, Dilation.NONE),
				ModelTransform.pivot(4.5f, 9.0f, 0.0f));

		root.addChild("arm_r", ModelPartBuilder.create()
						.uv(24, 30)
						.mirrored().cuboid(-3.0f, 0.0f, -2.0f, 3.0f, 10.0f, 3.0f, Dilation.NONE),
				ModelTransform.pivot(-4.5f, 9.0f, 0.0f));

		root.addChild("shards", ModelPartBuilder.create()
						.uv(40, 0)
						.cuboid(-1.0f, -1.0f, -7.0f, 2.0f, 6.0f, 2.0f, Dilation.NONE)
						.uv(40, 8)
						.cuboid(-1.0f, -1.0f, 5.0f, 2.0f, 6.0f, 2.0f, Dilation.NONE),
				ModelTransform.pivot(0.0f, 10.0f, 0.0f));

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(ShardWraithEntity entity, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yaw = netHeadYaw * MathHelper.RADIANS_PER_DEGREE;
		this.head.pitch = headPitch * MathHelper.RADIANS_PER_DEGREE;
		float bob = MathHelper.sin(ageInTicks * 0.3f);
		this.armL.pitch = bob * 0.4f - 0.2f;
		this.armR.pitch = -bob * 0.4f - 0.2f;
		this.shards.yaw = ageInTicks * 0.25f;
		this.body.pitch = 0.05f + MathHelper.sin(ageInTicks * 0.15f) * 0.03f;
	}
}
