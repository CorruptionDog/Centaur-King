package com.corruptdog.efat.world.capabilities.entitypatch.CombatBehaviors;

import com.corruptdog.efat.gameassets.UnHumanMobAnimations;
import com.corruptdog.efat.world.capabilities.entitypatch.boss.CentaurKingPatch;
import yesman.epicfight.world.capabilities.entitypatch.HumanoidMobPatch;
import yesman.epicfight.world.entity.ai.goal.CombatBehaviors;

public class UnHumanMobCombatBehaviors {

    public static final CombatBehaviors.Builder<CentaurKingPatch> CENTAURKING = CombatBehaviors.<CentaurKingPatch>builder()
                 .newBehaviorSeries(
                    CombatBehaviors.BehaviorSeries.<CentaurKingPatch>builder().weight(100.0F).canBeInterrupted(true).looping(false)
                           .nextBehavior(CombatBehaviors.Behavior.<CentaurKingPatch>builder().animationBehavior(UnHumanMobAnimations.CENTAURKING_ATTACK1).withinEyeHeight().withinDistance(0.0D, 2.0D))) ;


}
