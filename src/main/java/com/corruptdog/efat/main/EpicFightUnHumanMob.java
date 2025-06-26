package com.corruptdog.efat.main;

import com.corruptdog.efat.entity.UnHumanMobEntitys;
import com.corruptdog.efat.gameassets.UnHumanMobAnimations;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EpicFightUnHumanMob.MODID)
public class EpicFightUnHumanMob
{
    public static final String MODID = "efck";


    public EpicFightUnHumanMob(FMLJavaModLoadingContext context)
    {
        IEventBus bus = context.getModEventBus();
        UnHumanMobEntitys.ENTITIES.register(bus);
        bus.addListener(UnHumanMobAnimations::registerAnimations);
    }
}
