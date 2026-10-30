package com.example.lumendepths.entity;

import com.example.lumendepths.LumenItems;
import com.example.lumendepths.entity.goals.FlyAroundGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

/**
 * Glowmoth: a small glowing ambient moth that actively flies around caves.
 * Uses direct velocity steering (FlyAroundGoal) with gravity disabled.
 */
public class GlowmothEntity extends AmbientEntity {

public GlowmothEntity(EntityType<? extends GlowmothEntity> type, World world) {
super(type, world);
this.setNoGravity(true);
}

public static DefaultAttributeContainer.Builder createAttributes() {
return AmbientEntity.createMobAttributes()
.add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
.add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6);
}

@Override
protected void initGoals() {
this.goalSelector.add(1, new SwimGoal(this));
this.goalSelector.add(2, new FlyAroundGoal(this));
}

@Override
public void tick() {
super.tick();
if (!this.getWorld().isClient()) {
this.setNoGravity(true);
// Occasionally emit a soft glow particle while flying.
if (this.age % 25 == 0 && !this.getVelocity().isZero()) {
((net.minecraft.server.world.ServerWorld) this.getWorld())
.spawnParticles(ParticleTypes.END_ROD,
this.getX(), this.getY() + 0.3, this.getZ(), 1, 0.1, 0.1, 0.1, 0.0);
}
}
}

@Override
protected SoundEvent getAmbientSound() {
return SoundEvents.ENTITY_GLOW_SQUID_AMBIENT;
}

@Override
protected SoundEvent getHurtSound(DamageSource source) {
return SoundEvents.ENTITY_GENERIC_HURT;
}

@Override
protected SoundEvent getDeathSound() {
return SoundEvents.ENTITY_GLOW_SQUID_DEATH;
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
