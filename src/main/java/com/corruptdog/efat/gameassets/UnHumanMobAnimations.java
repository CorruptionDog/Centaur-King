package com.corruptdog.efat.gameassets;

import com.corruptdog.efat.main.EpicFightUnHumanMob;
import com.corruptdog.efat.model.armature.CentaurKingArmature;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.OBBCollider;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.world.damagesource.StunType;

public class UnHumanMobAnimations {
	public static AnimationManager.AnimationAccessor<StaticAnimation> CENTAURKING_IDLE;
	public static AnimationManager.AnimationAccessor<LongHitAnimation> CENTAURKING_DEATH;
	public static AnimationManager.AnimationAccessor<MovementAnimation> CENTAURKING_RUN;
	public static AnimationManager.AnimationAccessor<MovementAnimation> CENTAURKING_WALK;

	public static AnimationManager.AnimationAccessor<ActionAnimation> CENTAURKING_APPEAR;
	public static AnimationManager.AnimationAccessor<ActionAnimation> CENTAURKING_BLOCK;
	public static AnimationManager.AnimationAccessor<ActionAnimation> CENTAURKING_BREAK_DEFENSE;
	public static AnimationManager.AnimationAccessor<ActionAnimation> CENTAURKING_EXECUTED;
	public static AnimationManager.AnimationAccessor<ActionAnimation> CENTAURKING_HURT_A;
	public static AnimationManager.AnimationAccessor<ActionAnimation> CENTAURKING_HURT_B;
	public static AnimationManager.AnimationAccessor<ActionAnimation> CENTAURKING_STAGE_2;
	public static AnimationManager.AnimationAccessor<ActionAnimation> CENTAURKING_TURN;
	public static AnimationManager.AnimationAccessor<ActionAnimation> CENTAURKING_TURN_180;


	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> CENTAURKING_ATTACK_A;
	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> CENTAURKING_ATTACK_B;
	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> CENTAURKING_ATTACK_C;
	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> CENTAURKING_ATTACK_D;
	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> CENTAURKING_ATTACK_E;
	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> CENTAURKING_BOUNCE_ATTACK;
	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> CENTAURKING_DASH_A;
	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> CENTAURKING_DASH_B;
	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> CENTAURKING_HEAVY_ATTACK_A;
	public static AnimationManager.AnimationAccessor<BasicAttackAnimation> CENTAURKING_HEAVY_ATTACK_B;
	public static AnimationManager.AnimationAccessor<AttackAnimation> CENTAURKING_JUMPATK;


	public static final Collider weaponCollider_Preset = new OBBCollider(0.5, 1.5, 0.5, 0.0F, 0.0F, 0);

	@SubscribeEvent
	public static void registerAnimations(AnimationManager.AnimationRegistryEvent event) {
		event.newBuilder(EpicFightUnHumanMob.MODID, UnHumanMobAnimations::buildAnimations);
	}

	public static void buildAnimations(AnimationManager.AnimationBuilder builder) {


		Armatures.ArmatureAccessor<CentaurKingArmature> ckarmature = UnHumanMobArmatures.CENTAURKING;

		CENTAURKING_IDLE = builder.nextAccessor("centaurking/living/centaurking_idle", (accessor) -> new StaticAnimation(true, accessor, ckarmature));

		CENTAURKING_DEATH = builder.nextAccessor("centaurking/living/centaurking_die", (accessor) -> new LongHitAnimation(0.1f, accessor, ckarmature));

		CENTAURKING_RUN = builder.nextAccessor("centaurking/living/centaurking_run", (accessor) -> new MovementAnimation(true, accessor, ckarmature));

		CENTAURKING_WALK = builder.nextAccessor("centaurking/living/centaurking_walk", (accessor) -> new MovementAnimation(true, accessor, ckarmature));

		CENTAURKING_APPEAR = builder.nextAccessor("centaurking/centaurking_appear", (accessor) -> new ActionAnimation(0.1f, accessor, ckarmature));

		CENTAURKING_BLOCK = builder.nextAccessor("centaurking/centaurking_block", (accessor) -> new ActionAnimation(0.1f, accessor, ckarmature));

		CENTAURKING_BREAK_DEFENSE = builder.nextAccessor("centaurking/centaurking_break_defense", (accessor) -> new ActionAnimation(0.1f, accessor, ckarmature));

		CENTAURKING_EXECUTED = builder.nextAccessor("centaurking/centaurking_executed", (accessor) -> new ActionAnimation(0.1f, accessor, ckarmature));

		CENTAURKING_HURT_A = builder.nextAccessor("centaurking/centaurking_hurt_a", (accessor) -> new ActionAnimation(0.1f, accessor, ckarmature));

		CENTAURKING_HURT_A = builder.nextAccessor("centaurking/centaurking_hurt_b", (accessor) -> new ActionAnimation(0.1f, accessor, ckarmature));

		CENTAURKING_STAGE_2 = builder.nextAccessor("centaurking/centaurking_stage_2", (accessor) -> new ActionAnimation(0.1f, accessor, ckarmature));

		CENTAURKING_TURN = builder.nextAccessor("centaurking/centaurking_turn", (accessor) -> new ActionAnimation(0.1f, accessor, ckarmature));

		CENTAURKING_TURN_180 = builder.nextAccessor("centaurking/centaurking_turn_180", (accessor) -> new ActionAnimation(0.1f, accessor, ckarmature));

		CENTAURKING_JUMPATK = builder.nextAccessor("centaurking/combat/centaurking_jumpatk", (accessor) -> new AttackAnimation(0.05F, accessor, ckarmature,
				new AttackAnimation.Phase(0.0F, 2.0F, 2.1F, Float.MAX_VALUE, Float.MAX_VALUE, InteractionHand.MAIN_HAND, ckarmature.get().rootJoint, ColliderPreset.BIPED_BODY_COLLIDER)
						.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN))
				.addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.45F)
				.addStateRemoveOld(EntityState.TURNING_LOCKED, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
				.addEvents(
						AnimationEvent.InTimeEvent.create(1.9F, (entitypatch, self, params) -> entitypatch.getOriginal().resetFallDistance(), AnimationEvent.Side.SERVER),
						AnimationEvent.InTimeEvent.create(2.1F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
								AnimationEvent.Side.CLIENT).params(new Vec3f(0.5F, -0.24F, 2.0F), ckarmature.get().rootJoint, 2.55D, 0.55F))
				.addProperty(
						AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> {
							if (elapsedTime >= 2.1F && elapsedTime < Float.MAX_VALUE) {
								float dpx = (float) entitypatch.getOriginal().getX();
								float dpy = (float) entitypatch.getOriginal().getY();
								float dpz = (float) entitypatch.getOriginal().getZ();
								for (BlockState block = entitypatch.getOriginal().level().getBlockState(new BlockPos.MutableBlockPos(dpx, dpy, dpz)); (block.getBlock() instanceof BushBlock || block.isAir()) && !block.is(Blocks.VOID_AIR); block = entitypatch.getOriginal().level().getBlockState(new BlockPos.MutableBlockPos(dpx, dpy, dpz))) {
									--dpy;
								}
								float distanceToGround = (float) Math.max(Math.abs(entitypatch.getOriginal().getY() - (double) dpy) - 1.0, 0.0);
								return 1.0F - (1.0F / (-distanceToGround - 1.0F) + 1.0F);
							} else {
								return speed;
							}
						}));


		CENTAURKING_ATTACK_A = builder.nextAccessor("centaurking/combat/centaurking_atk_a", (accessor) ->
				new BasicAttackAnimation(0.1f, 0.5f, 0.8f, 2.7f, weaponCollider_Preset, ckarmature.get().hand_r, accessor, ckarmature));

		CENTAURKING_ATTACK_B = builder.nextAccessor("centaurking/combat/centaurking_atk_b", (accessor) ->
				new BasicAttackAnimation(0.1f, 0.5f, 0.8f, 2.7f, weaponCollider_Preset, ckarmature.get().hand_r, accessor, ckarmature));

		CENTAURKING_ATTACK_C = builder.nextAccessor("centaurking/combat/centaurking_atk_c", (accessor) ->
				new BasicAttackAnimation(0.1f, 0.5f, 0.8f, 2.6f, weaponCollider_Preset, ckarmature.get().hand_r, accessor, ckarmature));

		CENTAURKING_ATTACK_D = builder.nextAccessor("centaurking/combat/centaurking_atk_d", (accessor) ->
				new BasicAttackAnimation(0.1f, 0.5f, 0.8f, 2.55f, weaponCollider_Preset, ckarmature.get().hand_r, accessor, ckarmature));

		CENTAURKING_ATTACK_E = builder.nextAccessor("centaurking/combat/centaurking_atk_e", (accessor) ->
				new BasicAttackAnimation(0.1f, 0.5f, 0.8f, 1.35f, weaponCollider_Preset, ckarmature.get().hand_r, accessor, ckarmature));

		CENTAURKING_BOUNCE_ATTACK = builder.nextAccessor("centaurking/combat/centaurking_bounce_atk", (accessor) ->
				new BasicAttackAnimation(0.1f, 0.5f, 0.8f, 1.35f, weaponCollider_Preset, ckarmature.get().hand_r, accessor, ckarmature));


		CENTAURKING_DASH_A = builder.nextAccessor("centaurking/combat/centaurking_dash_a", (accessor) ->
				new BasicAttackAnimation(0.1f, 0.5f, 0.8f, 3f, weaponCollider_Preset, ckarmature.get().hand_r, accessor, ckarmature));

		CENTAURKING_DASH_B = builder.nextAccessor("centaurking/combat/centaurking_dash_b", (accessor) ->
				new BasicAttackAnimation(0.1f, 0.5f, 0.8f, 2.7f, weaponCollider_Preset, ckarmature.get().hand_r, accessor, ckarmature));


		CENTAURKING_HEAVY_ATTACK_A = builder.nextAccessor("centaurking/combat/centaurking_heavy_atk_a", (accessor) ->
				new BasicAttackAnimation(0.1f, 0.5f, 0.8f, 3f, weaponCollider_Preset, ckarmature.get().hand_r, accessor, ckarmature));

		CENTAURKING_HEAVY_ATTACK_B = builder.nextAccessor("centaurking/combat/centaurking_heavy_atk_b", (accessor) ->
				new BasicAttackAnimation(0.1f, 0.5f, 0.8f, 2.7f, weaponCollider_Preset, ckarmature.get().hand_r, accessor, ckarmature));


	}
}
