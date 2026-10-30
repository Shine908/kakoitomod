package com.example.lumendepths.entity;

import com.example.lumendepths.LumenItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

/**
 * Gloom Maw: a fast hostile cave predator attacking players in melee.
 */
public class GloomMawEntity extends HostileEntity {

public GloomMawEntity(EntityType<? extends GloomMawEntity> type, World world) {
super(type, world);
}

public static DefaultAttributeContainer.Builder createAttributes() {
return HostileEntity.createHostileAttributes()
.add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.28)
.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
.add(EntityAttributes.GENERIC_ARMOR, 2.0);
}

@Override
protected void initGoals() {
this.goalSelector.add(0, new SwimGoal(this));
this.goalSelector.add(1, new MeleeAttackGoal(this, 1.15, true));
this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.9));
this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 10.0f));
this.goalSelector.add(6, new LookAroundGoal(this));
this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
}

@Override
protected SoundEvent getAmbientSound() {
return SoundEvents.ENTITY_ELDER_GUARDIAN_AMBIENT_LAND;
}

@Override
protected SoundEvent getHurtSound(DamageSource source) {
return SoundEvents.ENTITY_GENERIC_HURT;
}

@Override
protected SoundEvent getDeathSound() {
return SoundEvents.ENTITY_ELDER_GUARDIAN_DEATH_LAND;
}

@Override
protected void dropLoot(DamageSource source, boolean causedByPlayer) {
super.dropLoot(source, causedByPlayer);
this.dropItem(LumenItems.GLOOM_FANG, 1 + this.getRandom().nextInt(2));
if (this.getRandom().nextFloat() < 0.4f) {
this.dropItem(LumenItems.LUMEN_SHARD, 1);
}
}
}
