package com.corruptdog.efat.client.renderer;

import com.corruptdog.efat.entity.type.CentaurKing;
import com.corruptdog.efat.main.EpicFightUnHumanMob;
import com.corruptdog.efat.model.armature.EmptyEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class CKRenderer extends MobRenderer<CentaurKing, EmptyEntityModel<CentaurKing>> {

    public CKRenderer(EntityRendererProvider.Context context) {
        super(context, new EmptyEntityModel<>(), 0);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull CentaurKing arborTroll) {
        return ResourceLocation.fromNamespaceAndPath(EpicFightUnHumanMob.MODID,"textures/entity/centaurking.png");
    }
}