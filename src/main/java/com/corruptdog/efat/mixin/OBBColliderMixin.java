package com.corruptdog.efat.mixin;

import com.corruptdog.efat.entity.type.CentaurKingPartEntity;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.collider.OBBCollider;

@Mixin(value = OBBCollider.class, remap = false)
public abstract class OBBColliderMixin {

    @Shadow
    public abstract boolean isCollide(OBBCollider opponent);

    // tips:精细碰撞箱功能的核心逻辑！，对需要进行精细碰撞箱判定的实体进行判断分流
    @Inject(
            method = "isCollide(Lnet/minecraft/world/entity/Entity;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onIsCollideWithEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        // 跳过特定实体类型的碰撞检测
        if (entity instanceof CentaurKingPartEntity centaurKingPartEntity) {
            for (OBBCollider entityJointObbCollider : centaurKingPartEntity.entityOBBColliderMap.values()) {
                if (this.isCollide(entityJointObbCollider)) {
                    cir.setReturnValue(true);
                    return;
                }
            }
            // 如果没有与自定义joint碰撞箱碰撞，则返回false
            cir.setReturnValue(false);
            return;
        }

        // 如果不调用cir.setReturnValue，原方法会继续执行
    }
}
