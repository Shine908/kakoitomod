package com.example.lumendepths.entity;

import com.example.lumendepths.LumenItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

/**
 * Crystal Beetle: a slow, shy passive ground creature of the Lumen Caves.
 */
public class CrystalBeetleEntity extends PathAwareEntity {

public CrystalBeetleEntity(EntityType<? extends CrystalBeetleEntity> type, World world) {
super(type, world);
}

public static DefaultAttributeContainer.Builder createAttributes() {
return MobEntity.createMobAttributes()
.add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0)
.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.18)
.add(EntityAttributes.GENERIC_ARMOR, 2.0);
}

@Override
protected void initGoals() {
this.goalSelector.add(0, new SwimGoal(this));
this.goalSelector.add(1, new FleeEntityGoal<>(this, PlayerEntity.class, 8.0f, 1.2, 1.5));
this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.8));
this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 5.0f));
this.goalSelector.add(6, new LookAroundGoal(this));
}

@Override
protected SoundEvent getAmbientSound() {
return SoundEvents.ENTITY_ENDERMITE_AMBIENT;
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
this.dropItem(LumenItems.CRYSTAL_DUST, 1 + this.getRandom().nextInt(2));
if (this.getRandom().nextFloat() < 0.25f) {
this.dropItem(LumenItems.LUMEN_SHARD, 1);
}
}
}
