package com.corruptdog.efat.world.capabilities.entitypatch.boss;

import com.corruptdog.efat.entity.type.CentaurKing;
import com.corruptdog.efat.gameassets.UnHumanMobAnimations;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.world.capabilities.entitypatch.Factions;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCategory;
import yesman.epicfight.world.damagesource.StunType;
import yesman.epicfight.world.entity.ai.goal.CombatBehaviors;

import java.util.Map;

public class CentaurKingPatch extends MobPatch<CentaurKing> {
    protected Map<WeaponCategory, Map<Style, CombatBehaviors.Builder<CentaurKingPatch>>> weaponAttackMotions;
    public CentaurKingPatch() {
        super(Factions.UNDEAD);
    }



//    @Override
//    protected void initAI() {
//        super.initAI();
//        this.original.goalSelector.addGoal(0, new AnimatedAttackGoal<>(this, UnHumanMobCombatBehaviors.CENTAURKING.build(this)));
//        this.original.goalSelector.addGoal(1, new TargetChasingGoal(this, this.original, 1.0D, false));
//    }

    @Override
    public float getYRotLimit() {
        return 20.0F;
    }

    @Override
    public void initAnimator(Animator animator) {
        super.initAnimator(animator);
        animator.addLivingAnimation(LivingMotions.IDLE, UnHumanMobAnimations.CENTAURKING_IDLE);
		animator.addLivingAnimation(LivingMotions.RUN, UnHumanMobAnimations.CENTAURKING_RUN);
		animator.addLivingAnimation(LivingMotions.WALK, UnHumanMobAnimations.CENTAURKING_WALK);
		animator.addLivingAnimation(LivingMotions.DEATH, UnHumanMobAnimations.CENTAURKING_DEATH);
    }

    @Override
    public void updateMotion(boolean considerInaction) {
        super.commonAggressiveMobUpdateMotion(considerInaction);
    }
    public AnimationManager.AnimationAccessor<? extends StaticAnimation> getHitAnimation(StunType stunType) {
        return null;
    }
}