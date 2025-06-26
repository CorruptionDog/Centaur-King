package com.corruptdog.efat.client.renderer;

import com.corruptdog.efat.client.CentaurKingMesh;
import com.corruptdog.efat.gameassets.mesh.UnHumanMobMeshs;
import com.corruptdog.efat.entity.type.CentaurKing;
import com.corruptdog.efat.model.armature.EmptyEntityModel;
import com.corruptdog.efat.world.capabilities.entitypatch.boss.CentaurKingPatch;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.client.renderer.patched.entity.PatchedLivingEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class PCentaurKingRenderer extends PatchedLivingEntityRenderer<CentaurKing, CentaurKingPatch, EmptyEntityModel<CentaurKing>, CKRenderer, CentaurKingMesh> {
    public PCentaurKingRenderer(EntityRendererProvider.Context context, EntityType<?> entityType) {
        super(context, entityType);
    }

    @Override
    public AssetAccessor<CentaurKingMesh> getDefaultMesh() {
        return UnHumanMobMeshs.CENTAURKING;
    }
}