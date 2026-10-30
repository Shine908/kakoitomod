package com.example.lumendepths.entity.model;
import com.example.lumendepths.LumenDepthsMod;

import com.example.lumendepths.entity.CrystalSnailEntity;
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
 * Snail with a soft foot base, a big crystal shell and two eye stalks.
 */
public class CrystalSnailEntityModel extends EntityModel<CrystalSnailEntity> {

	/** Model layer used by the renderer and client initializer. */
	public static final EntityModelLayer LAYER =
			new EntityModelLayer(Identifier.of(LumenDepthsMod.MOD_ID, "crystal_snail"), "main");

        private final ModelPart foot;
        private final ModelPart head;
        private final ModelPart shell;
        private final ModelPart leftHorn;
        private final ModelPart rightHorn;

        public CrystalSnailEntityModel(ModelPart root) {
                this.foot = root.getChild("foot");
                this.head = root.getChild("head");
                this.shell = root.getChild("shell");
                this.leftHorn = root.getChild("left_horn");
                this.rightHorn = root.getChild("right_horn");
        }

        public static TexturedModelData getTexturedModelData() {
                ModelData modelData = new ModelData();
                ModelPartData root = modelData.getRoot();

                root.addChild("foot", ModelPartBuilder.create()
                                .uv(0, 0)
                                .cuboid(-4.0f, -2.0f, -6.0f, 8.0f, 4.0f, 12.0f, Dilation.NONE),
                                ModelTransform.pivot(0.0f, 20.0f, 0.0f));

                root.addChild("head", ModelPartBuilder.create()
                                .uv(0, 16)
                                .cuboid(-2.0f, -3.0f, -3.0f, 4.0f, 4.0f, 4.0f, Dilation.NONE),
                                ModelTransform.pivot(0.0f, 18.0f, -6.0f));

                root.addChild("shell", ModelPartBuilder.create()
                                .uv(16, 16)
                                .cuboid(-4.0f, -7.0f, -4.0f, 8.0f, 8.0f, 8.0f, Dilation.NONE)
                                .uv(20, 32)
                                .cuboid(-2.0f, -9.0f, -2.0f, 4.0f, 3.0f, 4.0f, Dilation.NONE),
                                ModelTransform.pivot(0.0f, 15.0f, 1.0f));

                root.addChild("left_horn", ModelPartBuilder.create()
                                .uv(40, 8)
                                .cuboid(-0.5f, -4.0f, -0.5f, 1.0f, 4.0f, 1.0f, Dilation.NONE),
                                ModelTransform.pivot(1.5f, 15.0f, -7.0f));

                root.addChild("right_horn", ModelPartBuilder.create()
                                .uv(40, 8)
                                .cuboid(-0.5f, -4.0f, -0.5f, 1.0f, 4.0f, 1.0f, Dilation.NONE),
                                ModelTransform.pivot(-1.5f, 15.0f, -7.0f));

                return TexturedModelData.of(modelData, 64, 64);
        }

        @Override
        public void setAngles(CrystalSnailEntity entity, float limbSwing, float limbSwingAmount,
                        float ageInTicks, float netHeadYaw, float headPitch) {
                this.head.yaw = netHeadYaw * MathHelper.RADIANS_PER_DEGREE;
                this.head.pitch = headPitch * MathHelper.RADIANS_PER_DEGREE;
                this.leftHorn.pitch = this.head.pitch;
                this.rightHorn.pitch = this.head.pitch;
                this.leftHorn.yaw = MathHelper.sin(ageInTicks * 0.3f) * 0.15f;
                this.rightHorn.yaw = -MathHelper.sin(ageInTicks * 0.3f) * 0.15f;
                float scale = entity.isBaby() ? 0.6f : 1.0f;
                this.shell.xScale = scale;
                this.shell.yScale = scale;
                this.shell.zScale = scale;
        }


	@Override
	public void render(net.minecraft.client.util.math.MatrixStack matrixStack, net.minecraft.client.render.VertexConsumer vertexConsumer, int light, int overlay, int color) {
		this.foot.render(matrixStack, vertexConsumer, light, overlay, color);
		this.head.render(matrixStack, vertexConsumer, light, overlay, color);
		this.shell.render(matrixStack, vertexConsumer, light, overlay, color);
		this.leftHorn.render(matrixStack, vertexConsumer, light, overlay, color);
		this.rightHorn.render(matrixStack, vertexConsumer, light, overlay, color);
	}
}
