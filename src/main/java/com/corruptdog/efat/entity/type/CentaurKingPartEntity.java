package com.corruptdog.efat.entity.type;

import com.corruptdog.efat.entity.collider.EntityOBBCollider;
import com.corruptdog.efat.gameassets.UnHumanMobAnimations;
import com.corruptdog.efat.world.capabilities.entitypatch.boss.CentaurKingPatch;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yesman.epicfight.api.animation.*;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.HashMap;

public class CentaurKingPartEntity extends PartEntity<CentaurKing> {
    public final CentaurKing parentEntity;
    public final Joint followJoint;
    private final EntityDimensions size;
    public Vec3 offset;
    public HashMap<Joint, EntityOBBCollider> entityOBBColliderMap;

    public CentaurKingPartEntity(CentaurKing parent, float width, float height, Vec3 offset, Joint followJoint, HashMap<Joint, EntityOBBCollider> OBBMap) {
        super(parent);
        this.size = EntityDimensions.scalable(width, height);
        this.refreshDimensions();
        this.followJoint = followJoint;
        this.parentEntity = parent;
        this.offset = offset;
        this.entityOBBColliderMap = OBBMap;

    }

    @Override
    public boolean shouldRender(double p_20296_, double p_20297_, double p_20298_) {
        Boolean res = super.shouldRender(p_20296_, p_20297_, p_20298_);
        return res;
    }

    @Override
    public void tick() {
        CentaurKingPatch parentEntityPatch = EpicFightCapabilities.getEntityPatch(this.parentEntity, CentaurKingPatch.class);
        if (parentEntityPatch != null) {
            this.moveTo(getJointWorldPosWithOffset(parentEntityPatch, this.followJoint));
            SyncEntityJointOBBCollider(parentEntityPatch);
        }
    }

    public void SyncEntityJointOBBCollider(CentaurKingPatch parentEntityPatch) {
        AnimationPlayer player = parentEntityPatch.getAnimator().getPlayerFor(null);
        DynamicAnimation nowAnimation = player.getAnimation().get();
        float prevElapsedTime = player.getPrevElapsedTime();
        float ElapsedTime = player.getElapsedTime();
        float playSpeed = nowAnimation.getPlaySpeed(parentEntityPatch, nowAnimation);
        if (!parentEntityPatch.isLogicalClient() && nowAnimation == Animations.EMPTY_ANIMATION) {
            LivingMotion livingMotion = parentEntityPatch.getCurrentLivingMotion();
            nowAnimation = parentEntityPatch.getAnimator().getLivingAnimation(livingMotion, UnHumanMobAnimations.CENTAURKING_IDLE).get();
            prevElapsedTime = 0;
            ElapsedTime = 0;
        }
        for (Joint joint : this.entityOBBColliderMap.keySet()) {
            this.entityOBBColliderMap.get(joint).updateOBBCollider(parentEntityPatch, nowAnimation, prevElapsedTime, ElapsedTime, joint, playSpeed);
        }
    }

    public Vec3 getJointWorldPosWithOffset(LivingEntityPatch<?> entityPatch, Joint joint) {
        Animator animator = entityPatch.getAnimator();
        Pose pose = animator.getPlayerFor(null).getCurrentPose(entityPatch, 0.5F);

        Vec3 pos = entityPatch.getOriginal().position()
                .add(toWorldOffset(entityPatch.getOriginal().getYRot(), entityPatch.getOriginal().getXRot(), this.offset));
        OpenMatrix4f modelTf = OpenMatrix4f.createTranslation((float) pos.x, (float) pos.y, (float) pos.z)
                .mulBack(OpenMatrix4f.createRotatorDeg(180.0F, Vec3f.Y_AXIS)
                        .mulBack(entityPatch.getModelMatrix(1)));
        OpenMatrix4f JointTf = new OpenMatrix4f(entityPatch.getArmature().getBoundTransformFor(pose, joint));

        return OpenMatrix4f.transform(JointTf.mulFront(modelTf), Vec3.ZERO);
    }

    /**
     * 将自定义向量转换为世界坐标偏移（基于旋转角度）x为正则右，y为正则上，z为正则前
     *
     * @param yaw       水平旋转角度（yrot，度）
     * @param pitch     垂直旋转角度（xrot，度）
     * @param customVec 自定义向量（x: 左右，y: 上下，z: 前后）
     * @return 世界坐标系中的位移向量
     */
    public static Vec3 toWorldOffset(float yaw, float pitch, Vec3 customVec) {
        // 将角度转换为弧度
        double yawRad = java.lang.Math.toRadians(yaw);
        double pitchRad = java.lang.Math.toRadians(pitch);
        // 计算前方向量（基于yaw和pitch）
        double x = -java.lang.Math.sin(yawRad) * java.lang.Math.cos(pitchRad);
        double y = -java.lang.Math.sin(pitchRad);
        double z = java.lang.Math.cos(yawRad) * java.lang.Math.cos(pitchRad);
        Vec3 forward = new Vec3(x, y, z).normalize();
        // 计算右侧向量：前方向量与上向量(0,1,0)的叉积
        Vec3 up = new Vec3(0, 1, 0);
        Vec3 right = forward.cross(up).normalize();
        // 计算实际上方向：右侧向量与前方向量的叉积（确保垂直）
        Vec3 actualUp = right.cross(forward).normalize();
        // 根据自定义向量组合位移
        Vec3 offset = right.scale(customVec.x)  // 左右
                .add(actualUp.scale(customVec.y)) // 上下
                .add(forward.scale(customVec.z)); // 前后
        return offset;
    }


    @Override
    public boolean hurt(DamageSource p_19946_, float p_19947_) {
        System.out.println("part hurt");
        return this.parentEntity.hurt(p_19946_, p_19947_);
    }

    @Override
    public void lavaHurt() {
        super.lavaHurt();
    }

    public EntityDimensions getDimensions(net.minecraft.world.entity.Pose p_31023_) {
        return this.size;
    }

    public boolean is(@NotNull Entity pEntity) {
        return this == pEntity || this.parentEntity == pEntity;
    }

    public boolean isPickable() {
        return true;
    }

    @Nullable
    public ItemStack getPickResult() {
        return this.parentEntity.getPickResult();
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }
}
