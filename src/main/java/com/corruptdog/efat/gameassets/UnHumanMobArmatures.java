package com.corruptdog.efat.gameassets;

import com.corruptdog.efat.entity.UnHumanMobEntitys;
import com.corruptdog.efat.main.EpicFightUnHumanMob;
import com.corruptdog.efat.model.armature.CentaurKingArmature;
import yesman.epicfight.gameasset.Armatures;

public class UnHumanMobArmatures {
    public static Armatures.ArmatureAccessor<CentaurKingArmature> CENTAURKING = Armatures.ArmatureAccessor.create(EpicFightUnHumanMob.MODID, "entity/centaurking", CentaurKingArmature::new);

    public static void registerArmatures(){
        Armatures.registerEntityTypeArmature(UnHumanMobEntitys.CENTAURKING.get(), CENTAURKING);
    }
}
