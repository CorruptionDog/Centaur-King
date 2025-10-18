package com.corruptdog.efat.mixin;


import com.corruptdog.efat.entity.type.CentaurKing;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = net.minecraft.client.renderer.entity.EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {


    // tips:此处mixin仅用于取消掉原版的碰撞箱渲染，不然精细碰撞箱与原版多实体碰撞箱一起渲染，太眼花缭乱，不便于调试和展示，与功能无关，可直接删除
    @Inject(at = @At("HEAD"), method = "renderHitbox(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;Lnet/minecraft/world/entity/Entity;F)V", cancellable = true)
    private static void onRenderHitbox(PoseStack p_114442_, VertexConsumer p_114443_, Entity p_114444_, float p_114445_, CallbackInfo ci) {
        if (p_114444_ instanceof CentaurKing) {
            ci.cancel();
        }
    }
}