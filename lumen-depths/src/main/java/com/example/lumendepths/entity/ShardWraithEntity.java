package com.example.lumendepths.entity;

import com.example.lumendepths.LumenItems;
import com.example.lumendepths.entity.goals.FlyChaseGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

/**
 * Shard Wraith: a hostile floating ghost made of crystal shards.
 * Chases players in the air and damages them on contact.
 */
public class ShardWraithEntity extends HostileEntity {

private int contactAttackCooldown;

public ShardWraithEntity(EntityType<? extends ShardWraithEntity> type, World world) {
super(type, world);
this.setNoGravity(true);
}

public static DefaultAttributeContainer.Builder createAttributes() {
return HostileEntity.createHostileAttributes()
.add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0)
.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
.add(EntityAttributes.GENERIC_FLYING_SPEED, 0.5)
.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0);
}

@Override
protected void initGoals() {
this.goalSelector.add(0, new SwimGoal(this));
this.goalSelector.add(1, new FlyChaseGoal(this, 1.0));
this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
}

@Override
public void tick() {
super.tick();
if (!this.getWorld().isClient()) {
this.setNoGravity(true);
if (this.contactAttackCooldown > 0) {
this.contactAttackCooldown--;
}
// Contact damage when touching the target.
var target = this.getTarget();
if (target != null && target.isAlive() && this.contactAttackCooldown == 0
&& this.distanceTo(target) < 1.6) {
if (target.damage(this.getWorld().getDamageSources().mobAttack(this),
(float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE))) {
target.knockback(0.4,
this.getX() - target.getX(), this.getZ() - target.getZ());
}
this.contactAttackCooldown = 30;
}
}
}

@Override
protected SoundEvent getAmbientSound() {
return SoundEvents.ENTITY_ELDER_GUARDIAN_AMBIENT;
}

@Override
protected SoundEvent getHurtSound(DamageSource source) {
return SoundEvents.ENTITY_GENERIC_HURT;
}

@Override
protected SoundEvent getDeathSound() {
return SoundEvents.ENTITY_ELDER_GUARDIAN_DEATH;
}

@Override
protected void dropLoot(DamageSource source, boolean causedByPlayer) {
super.dropLoot(source, causedByPlayer);
this.dropItem(LumenItems.LUMEN_SHARD, 1 + this.getRandom().nextInt(2));
if (this.getRandom().nextFloat() < 0.5f) {
this.dropItem(LumenItems.WISP_ESSENCE, 1);
}
if (this.getRandom().nextFloat() < 0.1f) {
this.dropItem(LumenItems.AURORA_GEM, 1);
}
}
}
