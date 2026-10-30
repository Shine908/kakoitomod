package com.example.lumendepths.entity;

import com.example.lumendepths.LumenEntities;
import com.example.lumendepths.LumenItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

/**
 * Crystal Snail: a slow, passive creature with a glowing crystal shell.
 */
public class CrystalSnailEntity extends AnimalEntity {

	public CrystalSnailEntity(EntityType<? extends CrystalSnailEntity> type, World world) {
		super(type, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return AnimalEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.12);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new AnimalMateGoal(this, 1.0));
		this.goalSelector.add(2, new TemptGoal(this, 1.1, CrystalSnailEntity::isTemptItem, false));
		this.goalSelector.add(3, new FollowParentGoal(this, 1.0));
		this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
		this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
		this.goalSelector.add(6, new LookAroundGoal(this));
	}

	private static boolean isTemptItem(ItemStack stack) {
		return stack.isOf(Items.SUGAR) || stack.isOf(LumenItems.AURORA_FRUIT);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.isOf(Items.SUGAR);
	}

	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity parent) {
		CrystalSnailEntity child = LumenEntities.CRYSTAL_SNAIL.create(world);
		if (child != null) {
			child.setHealth(child.getMaxHealth());
		}
		return child;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.BLOCK_SLIME_BLOCK_STEP;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_GENERIC_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_TURTLE_DEATH;
	}

	@Override
	protected void dropLoot(DamageSource source, boolean causedByPlayer) {
		super.dropLoot(source, causedByPlayer);
		int count = 1 + this.getRandom().nextInt(2);
		this.dropItem(LumenItems.LUMEN_SHARD, count);
		if (this.getRandom().nextFloat() < 0.2f) {
			this.dropItem(LumenItems.WISP_ESSENCE, 1);
		}
	}
}
