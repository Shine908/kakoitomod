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
 * Lumen Sprite: a passive glowing flying spirit of the Lumen Caves.
 */
public class LumenSpriteEntity extends AmbientEntity {

public LumenSpriteEntity(EntityType<? extends LumenSpriteEntity> type, World world) {
super(type, world);
this.setNoGravity(true);
}

public static DefaultAttributeContainer.Builder createAttributes() {
return AmbientEntity.createMobAttributes()
.add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
.add(EntityAttributes.GENERIC_FLYING_SPEED, 0.7);
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
if (this.age % 12 == 0) {
((net.minecraft.server.world.ServerWorld) this.getWorld())
.spawnParticles(ParticleTypes.PORTAL,
this.getX(), this.getY() + 0.4, this.getZ(), 2, 0.2, 0.2, 0.2, 0.02);
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
return SoundEvents.ENTITY_ENDERMITE_DEATH;
}

@Override
protected void dropLoot(DamageSource source, boolean causedByPlayer) {
super.dropLoot(source, causedByPlayer);
if (causedByPlayer) {
this.dropItem(LumenItems.WISP_ESSENCE, 1 + this.getRandom().nextInt(2));
if (this.getRandom().nextFloat() < 0.3f) {
this.dropItem(LumenItems.CRYSTAL_DUST, 1);
}
}
}
}
