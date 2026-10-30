package com.example.lumendepths.entity.goals;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

/**
 * Simple custom flying wander goal: picks a random air position near the mob
 * and steers toward it using direct velocity control (no pathfinding needed).
 */
public class FlyAroundGoal extends Goal {

private final MobEntity entity;
private Vec3d target;
private int restTicks;

public FlyAroundGoal(MobEntity entity) {
this.entity = entity;
this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
}

@Override
public boolean canStart() {
return this.target == null && this.restTicks <= 0;
}

@Override
public void start() {
Vec3d pos = this.entity.getPos();
double r = 6.0 + this.entity.getRandom().nextDouble() * 8.0;
double angle = this.entity.getRandom().nextDouble() * Math.PI * 2.0;
BlockPos next = BlockPos.ofFloored(
pos.x + Math.cos(angle) * r,
pos.y + (this.entity.getRandom().nextDouble() - 0.5) * 4.0,
pos.z + Math.sin(angle) * r);
this.target = Vec3d.ofCenter(next);
}

@Override
public boolean shouldContinue() {
return this.target != null;
}

@Override
public void tick() {
if (this.restTicks > 0) {
this.restTicks--;
}
if (this.target == null) {
return;
}
Vec3d delta = this.target.subtract(this.entity.getPos());
if (delta.lengthSquared() < 1.0) {
this.target = null;
this.restTicks = 20 + this.entity.getRandom().nextInt(60);
return;
}
Vec3d move = delta.normalize().multiply(0.07);
this.entity.setVelocity(move);
this.entity.setYaw((float) Math.toDegrees(-Math.atan2(move.x, move.z)));
this.entity.setPitch((float) Math.toDegrees(-Math.atan2(move.y, Math.hypot(move.x, move.z))));
}

@Override
public void end() {
this.target = null;
}

}
