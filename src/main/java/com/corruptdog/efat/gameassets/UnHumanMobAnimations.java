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
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.BasicAttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.damagesource.StunType;

public class UnHumanMobAnimations {
    public static AnimationManager.AnimationAccessor<StaticAnimation> CENTAURKING_IDLE;
    public static AnimationManager.AnimationAccessor<AttackAnimation> CENTAURKING_JUMPATK;
    public static AnimationManager.AnimationAccessor<BasicAttackAnimation> CENTAURKING_ATTACK1;
    @SubscribeEvent
    public static void registerAnimations(AnimationManager.AnimationRegistryEvent event){
        event.newBuilder(EpicFightUnHumanMob.MODID, UnHumanMobAnimations::buildAnimations);
    }
    public static void buildAnimations(AnimationManager.AnimationBuilder builder) {

        Armatures.ArmatureAccessor<CentaurKingArmature> ckarmature = UnHumanMobArmatures.CENTAURKING;

        CENTAURKING_IDLE = builder.nextAccessor("centaurking/living/centaurking_idle", (accessor) -> new StaticAnimation(true, accessor, ckarmature));

        CENTAURKING_JUMPATK =  builder.nextAccessor( "centaurking/combat/centaurking_jumpatk", (accessor) -> new AttackAnimation(0.05F, accessor, ckarmature,
                new AttackAnimation.Phase(0.0F, 2.0F, 2.1F,  Float.MAX_VALUE,Float.MAX_VALUE, InteractionHand.MAIN_HAND, ckarmature.get().rootJoint, ColliderPreset.BIPED_BODY_COLLIDER)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE,StunType.KNOCKDOWN))
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.45F)
                .addStateRemoveOld(EntityState.TURNING_LOCKED, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addEvents(
                        AnimationEvent.InTimeEvent.create(1.9F, (entitypatch, self, params) -> entitypatch.getOriginal().resetFallDistance(), AnimationEvent.Side.SERVER),
                        AnimationEvent.InTimeEvent.create(2.1F, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE,
                                AnimationEvent.Side.CLIENT).params(new Vec3f(0.5F, -0.24F, 2.0F),  ckarmature.get().rootJoint, 2.55D, 0.55F))
                .addProperty(
                        AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> {
                            if (elapsedTime >= 2.1F && elapsedTime < Float.MAX_VALUE) {
                                float dpx = (float) entitypatch.getOriginal(). getX();
                                float dpy = (float) entitypatch.getOriginal(). getY();
                                float dpz = (float) entitypatch.getOriginal(). getZ();
                                for(BlockState block = entitypatch.getOriginal().level().getBlockState(new BlockPos.MutableBlockPos(dpx, dpy, dpz)); (block.getBlock() instanceof BushBlock ||  block.isAir()) && !block.is(Blocks.VOID_AIR); block = entitypatch.getOriginal().level().getBlockState(new BlockPos.MutableBlockPos(dpx, dpy, dpz))) {
                                    --dpy;
                                }
                                float distanceToGround = (float)Math.max(Math.abs(entitypatch.getOriginal().getY() - (double)dpy) - 1.0, 0.0);
                                return 1.0F - (1.0F / (-distanceToGround - 1.0F) + 1.0F);
                            } else {
                                return speed;
                            }
                        }));

 }
}
