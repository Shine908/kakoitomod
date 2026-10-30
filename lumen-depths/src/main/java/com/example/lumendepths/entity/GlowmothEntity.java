package com.example.lumendepths.entity;

import com.example.lumendepths.LumenItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Glowmoth: a small, simple ambient flying mob that gently hovers around.
 */
public class GlowmothEntity extends AmbientEntity {

	public GlowmothEntity(EntityType<? extends GlowmothEntity> type, World world) {
		super(type, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return AmbientEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15)
				.add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new SwimGoal(this));
		this.goalSelector.add(2, new LookAroundGoal(this));
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.getWorld().isClient()) {
			this.setNoGravity(true);
			// Gentle hovering drift so the mob visibly floats around.
			if (this.age % 80 == 0 || this.getVelocity().lengthSquared() < 0.001) {
				double strength = 0.03 + this.getRandom().nextDouble() * 0.04;
				double angle = this.getRandom().nextDouble() * Math.PI * 2.0;
				this.setVelocity(new Vec3d(
						Math.cos(angle) * strength,
						(this.getRandom().nextDouble() - 0.4) * 0.04,
						Math.sin(angle) * strength));
			}
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_PARROT_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_GENERIC_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_GENERIC_SMALL_FALL;
	}

	@Override
	protected void dropLoot(DamageSource source, boolean causedByPlayer) {
		super.dropLoot(source, causedByPlayer);
		if (causedByPlayer) {
			this.dropItem(LumenItems.WISP_ESSENCE, 1 + this.getRandom().nextInt(2));
			if (this.getRandom().nextFloat() < 0.25f) {
				this.dropItem(LumenItems.LUMEN_SHARD, 1);
			}
		}
	}
}
