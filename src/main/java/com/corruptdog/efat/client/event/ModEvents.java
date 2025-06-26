package com.corruptdog.efat.client.event;


import com.corruptdog.efat.entity.type.CentaurKing;
import com.corruptdog.efat.gameassets.UnHumanMobArmatures;
import com.corruptdog.efat.entity.UnHumanMobEntitys;
import com.corruptdog.efat.world.capabilities.entitypatch.boss.CentaurKingPatch;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yesman.epicfight.api.forgeevent.EntityPatchRegistryEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void setPatch(EntityPatchRegistryEvent event) {
        event.getTypeEntry().put(UnHumanMobEntitys.CENTAURKING.get(),(entity -> CentaurKingPatch::new));
    }


    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(UnHumanMobArmatures::registerArmatures);
    }


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(UnHumanMobEntitys.CENTAURKING.get(), CentaurKing.createAttributes().build());
    }
}