package com.corruptdog.efat.entity;

import com.corruptdog.efat.entity.type.CentaurKing;
import com.corruptdog.efat.main.EpicFightUnHumanMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



public class UnHumanMobEntitys {
    public static final DeferredRegister<EntityType<?>> ENTITIES;
    public static final RegistryObject<EntityType<CentaurKing>> CENTAURKING;
    static {
        ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EpicFightUnHumanMob.MODID);
        CENTAURKING = ENTITIES.register("centaurking", () -> Builder.of(CentaurKing::new, MobCategory.CREATURE).sized(1.8F, 1.8F).clientTrackingRange(12).build("centaurking"));
    }
}
