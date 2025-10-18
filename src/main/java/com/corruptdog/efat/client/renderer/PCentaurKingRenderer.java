package com.corruptdog.efat.client.renderer;

import com.corruptdog.efat.client.CentaurKingMesh;
import com.corruptdog.efat.entity.collider.EntityOBBCollider;
import com.corruptdog.efat.entity.type.CentaurKingPartEntity;
import com.corruptdog.efat.gameassets.mesh.UnHumanMobMeshs;
import com.corruptdog.efat.entity.type.CentaurKing;
import com.corruptdog.efat.model.armature.EmptyEntityModel;
import com.corruptdog.efat.world.capabilities.entitypatch.boss.CentaurKingPatch;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.Nullable;
import yesman.epicfight.api.animation.Joint;
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

    @Override
    public void render(CentaurKing entity, CentaurKingPatch entitypatch, CKRenderer renderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, float partialTicks) {
        // 直接渲染全时态的纯真OBB变换顶点
        if(Minecraft.getInstance().getEntityRenderDispatcher().shouldRenderHitBoxes()){
            for (@Nullable PartEntity<?> part : entitypatch.getOriginal().getParts()) {
                for (Joint joint : ((CentaurKingPartEntity) part).entityOBBColliderMap.keySet()) {
                    EntityOBBCollider collider = ((CentaurKingPartEntity) part).entityOBBColliderMap.get(joint);
                    collider.drawInstantly(poseStack, Minecraft.getInstance().renderBuffers().bufferSource(), 0xFFB0E0E6     ,entity.position());
                }
            }
        }

        super.render(entity, entitypatch, renderer, buffer, poseStack, packedLight, partialTicks);

    }
}