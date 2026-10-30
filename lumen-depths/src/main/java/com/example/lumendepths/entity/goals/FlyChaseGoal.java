package com.example.lumendepths.entity.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

/**
 * Simple air chase goal: steers the mob directly toward its target using
 * velocity (no ground pathfinding). Works together with no-gravity mobs.
 */
public class FlyChaseGoal extends Goal {

private final MobEntity entity;
private final double speed;
private LivingEntity target;

public FlyChaseGoal(MobEntity entity, double speed) {
this.entity = entity;
this.speed = speed;
this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
}

@Override
public boolean canStart() {
LivingEntity t = this.entity.getTarget();
return t != null && t.isAlive() && t.squaredDistanceTo(this.entity) > 1.0;
}

@Override
public void start() {
this.target = this.entity.getTarget();
}

@Override
public boolean shouldContinue() {
return this.target != null && this.target.isAlive();
}

@Override
public void tick() {
if (this.target == null) {
return;
}
Vec3d delta = this.target.getPos().add(0, 0.4, 0).subtract(this.entity.getPos());
double len = delta.length();
if (len < 0.0001) {
return;
}
Vec3d move = delta.normalize().multiply(this.speed * 0.09);
this.entity.setVelocity(move);
this.entity.setYaw((float) Math.toDegrees(-Math.atan2(move.x, move.z)));
this.entity.setPitch((float) Math.toDegrees(-Math.atan2(move.y, Math.hypot(move.x, move.z))));
}

public void end() {
this.target = null;
}
}
