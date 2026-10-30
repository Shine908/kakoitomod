package com.example.lumendepths.entity.model;
import com.example.lumendepths.LumenDepthsMod;

import com.example.lumendepths.entity.GlowmothEntity;
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
 * Simple glowing moth: body, head, two antennae and two flapping wings.
 */
public class GlowmothEntityModel extends EntityModel<GlowmothEntity> {

	/** Model layer used by the renderer and client initializer. */
	public static final EntityModelLayer LAYER =
			new EntityModelLayer(Identifier.of(LumenDepthsMod.MOD_ID, "glowmoth"), "main");

        private final ModelPart body;
        private final ModelPart head;
        private final ModelPart leftWing;
        private final ModelPart rightWing;

        public GlowmothEntityModel(ModelPart root) {
                this.body = root.getChild("body");
                this.head = root.getChild("head");
                this.leftWing = root.getChild("left_wing");
                this.rightWing = root.getChild("right_wing");
        }

        public static TexturedModelData getTexturedModelData() {
                ModelData modelData = new ModelData();
                ModelPartData root = modelData.getRoot();

                root.addChild("body", ModelPartBuilder.create()
                                .uv(0, 0)
                                .cuboid(-1.5f, -1.0f, -3.0f, 3.0f, 2.0f, 6.0f, Dilation.NONE),
                                ModelTransform.pivot(0.0f, 12.0f, 0.0f));

                root.addChild("head", ModelPartBuilder.create()
                                .uv(0, 8)
                                .cuboid(-1.5f, -1.5f, -2.0f, 3.0f, 3.0f, 2.0f, Dilation.NONE)
                                .uv(12, 0)
                                .cuboid(-2.0f, -3.5f, -1.5f, 1.0f, 2.0f, 1.0f, Dilation.NONE)
                                .uv(17, 0)
                                .cuboid(1.0f, -3.5f, -1.5f, 1.0f, 2.0f, 1.0f, Dilation.NONE),
                                ModelTransform.pivot(0.0f, 12.0f, -3.0f));

                root.addChild("left_wing", ModelPartBuilder.create()
                                .uv(12, 4)
                                .cuboid(0.0f, -0.5f, -3.0f, 6.0f, 1.0f, 6.0f, Dilation.NONE),
                                ModelTransform.pivot(1.5f, 11.5f, 0.0f));

                root.addChild("right_wing", ModelPartBuilder.create()
                                .uv(12, 4)
                                .mirrored().cuboid(-6.0f, -0.5f, -3.0f, 6.0f, 1.0f, 6.0f, Dilation.NONE),
                                ModelTransform.pivot(-1.5f, 11.5f, 0.0f));

                return TexturedModelData.of(modelData, 64, 64);
        }

        @Override
        public void setAngles(GlowmothEntity entity, float limbSwing, float limbSwingAmount,
                        float ageInTicks, float netHeadYaw, float headPitch) {
                this.head.yaw = netHeadYaw * MathHelper.RADIANS_PER_DEGREE;
                this.head.pitch = headPitch * MathHelper.RADIANS_PER_DEGREE;
                float flap = MathHelper.sin(ageInTicks * 1.4f) * 0.9f;
                this.leftWing.roll = -flap;
                this.rightWing.roll = flap;
                this.body.pitch = 0.1f + MathHelper.sin(ageInTicks * 0.4f) * 0.05f;
        }


	@Override
	public void render(net.minecraft.client.util.math.MatrixStack matrixStack, net.minecraft.client.render.VertexConsumer vertexConsumer, int light, int overlay, int color) {
		this.body.render(matrixStack, vertexConsumer, light, overlay, color);
		this.head.render(matrixStack, vertexConsumer, light, overlay, color);
		this.leftWing.render(matrixStack, vertexConsumer, light, overlay, color);
		this.rightWing.render(matrixStack, vertexConsumer, light, overlay, color);
	}
}
