package com.corruptdog.efat.client.event;


import com.corruptdog.efat.client.renderer.CKRenderer;
import com.corruptdog.efat.client.renderer.PCentaurKingRenderer;
import com.corruptdog.efat.entity.UnHumanMobEntitys;
import com.corruptdog.efat.main.EpicFightUnHumanMob;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;

@Mod.EventBusSubscriber(modid = EpicFightUnHumanMob.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(UnHumanMobEntitys.CENTAURKING.get(), CKRenderer::new);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)

    public static void onPatchedRenderer(PatchedRenderersEvent.Add event){
        event.addPatchedEntityRenderer(UnHumanMobEntitys.CENTAURKING.get(), entityType -> new PCentaurKingRenderer(event.getContext(),entityType).initLayerLast(event.getContext(),entityType));}
}