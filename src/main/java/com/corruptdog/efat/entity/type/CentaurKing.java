package com.corruptdog.efat.entity.type;

import com.corruptdog.efat.entity.collider.EntityOBBCollider;
import com.corruptdog.efat.gameassets.UnHumanMobArmatures;
import com.corruptdog.efat.main.EpicFightUnHumanMob;
import com.corruptdog.efat.model.armature.CentaurKingArmature;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

import java.util.HashMap;
import java.util.Objects;


public class CentaurKing extends Monster {
    private final CentaurKingPartEntity[] subEntities;
    protected final CentaurKingPartEntity head;
    protected final CentaurKingPartEntity chest;
    protected final CentaurKingPartEntity body;
    protected final CentaurKingPartEntity hand_Left;
    protected final CentaurKingPartEntity hand_Right;
    protected final CentaurKingPartEntity leg_Front_Left;
    protected final CentaurKingPartEntity leg_Front_Right;
    protected final CentaurKingPartEntity leg_Back_Left;
    protected final CentaurKingPartEntity leg_Back_Right;

    public HashMap<Joint, EntityOBBCollider> PartHeadOBBMap = new HashMap<>();
    public HashMap<Joint, EntityOBBCollider> PartChestOBBMap = new HashMap<>();
    public HashMap<Joint, EntityOBBCollider> PartBodyOBBMap = new HashMap<>();

    public HashMap<Joint, EntityOBBCollider> PartHandRightOBBMap = new HashMap<>();
    public HashMap<Joint, EntityOBBCollider> PartHandLeftOBBMap = new HashMap<>();

    public HashMap<Joint, EntityOBBCollider> PartLegFrontRightOBBMap = new HashMap<>();
    public HashMap<Joint, EntityOBBCollider> PartLegFrontLeftOBBMap = new HashMap<>();

    public HashMap<Joint, EntityOBBCollider> PartLegBackRightOBBMap = new HashMap<>();
    public HashMap<Joint, EntityOBBCollider> PartLegBackLeftOBBMap = new HashMap<>();


    public CentaurKing(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
        CentaurKingArmature armature = UnHumanMobArmatures.CENTAURKING.get();

        this.PartHeadOBBMap.put(armature.head, new EntityOBBCollider(0.4, 0.35, 0.3, 0, 0.15, 0));
        this.head = new CentaurKingPartEntity(this, 1F, 0.8F, new Vec3(0, -0.2, 0), armature.head, this.PartHeadOBBMap);

        this.PartChestOBBMap.put(armature.root_006, new EntityOBBCollider(0.5, 0.3, 0.5, 0, 0, 0));
        this.PartChestOBBMap.put(armature.root_005, new EntityOBBCollider(0.5, 0.3, 0.5, 0, 0, 0));
        this.chest = new CentaurKingPartEntity(this, 1.8F, 1.5F, new Vec3(0, -0.5, 0), armature.root_005, this.PartChestOBBMap);

        this.PartBodyOBBMap.put(armature.root_004, new EntityOBBCollider(0.6, 0.4, 0.6, 0, 0, 0));
        this.PartBodyOBBMap.put(armature.root_001, new EntityOBBCollider(0.5, 0.4, 0.5, 0, -0.15, 0.1));
        this.PartBodyOBBMap.put(armature.root_002, new EntityOBBCollider(0.5, 0.4, 0.5, 0, 0, 0));
        this.PartBodyOBBMap.put(armature.root_003, new EntityOBBCollider(0.5, 0.3, 0.5, 0, 0, 0));
        this.body = new CentaurKingPartEntity(this, 3F, 2F, new Vec3(0, -0.4, 0.5), armature.root_002, this.PartBodyOBBMap);

        this.PartHandLeftOBBMap.put(armature.up_arm_l, new EntityOBBCollider(0.3, 0.5, 0.3, 0, 0.3, 0));
        this.PartHandLeftOBBMap.put(armature.dm_arm_l, new EntityOBBCollider(0.25, 0.6, 0.25, 0, 0.35, 0));
        this.hand_Left = new CentaurKingPartEntity(this, 2F, 2F, new Vec3(0, -1, 0), armature.dm_arm_l, this.PartHandLeftOBBMap);

        this.PartHandRightOBBMap.put(armature.up_arm_r, new EntityOBBCollider(0.3, 0.5, 0.3, 0, 0.3, 0));
        this.PartHandRightOBBMap.put(armature.dm_arm_r, new EntityOBBCollider(0.25, 0.6, 0.25, 0, 0.35, 0));
        this.hand_Right = new CentaurKingPartEntity(this, 2F, 2F, new Vec3(0, -1, 0), armature.dm_arm_r, this.PartHandRightOBBMap);

        this.PartLegFrontLeftOBBMap.put(armature.f_thigh_l, new EntityOBBCollider(0.3, 0.5, 0.3, 0, 0, 0));
        this.PartLegFrontLeftOBBMap.put(armature.f_leg_l, new EntityOBBCollider(0.25, 0.5, 0.25, 0, 0, 0));
        this.PartLegFrontLeftOBBMap.put(armature.f_foot_l, new EntityOBBCollider(0.25, 0.5, 0.25, 0, 0, 0));
        this.leg_Front_Left = new CentaurKingPartEntity(this, 1.5F, 2.5F, new Vec3(0, -1.2, 0), armature.f_leg_l, this.PartLegFrontLeftOBBMap);

        this.PartLegFrontRightOBBMap.put(armature.f_thigh_r, new EntityOBBCollider(0.3, 0.5, 0.3, 0, 0, 0));
        this.PartLegFrontRightOBBMap.put(armature.f_leg_r, new EntityOBBCollider(0.25, 0.5, 0.25, 0, 0, 0));
        this.PartLegFrontRightOBBMap.put(armature.f_foot_r, new EntityOBBCollider(0.25, 0.5, 0.25, 0, 0, 0));
        this.leg_Front_Right = new CentaurKingPartEntity(this, 1.5F, 2.5F, new Vec3(0, -1.2, 0), armature.f_leg_r, this.PartLegFrontRightOBBMap);

        this.PartLegBackLeftOBBMap.put(armature.b_thigh_l, new EntityOBBCollider(0.3, 0.5, 0.3, 0, 0, -0.1));
        this.PartLegBackLeftOBBMap.put(armature.b_leg_l, new EntityOBBCollider(0.25, 0.5, 0.25, 0, 0.3, -0.1));
        this.PartLegBackLeftOBBMap.put(armature.b_foot_l, new EntityOBBCollider(0.25, 0.5, 0.25, 0, 0, -0.1));
        this.leg_Back_Left = new CentaurKingPartEntity(this, 1.5F, 2.5F, new Vec3(0.0, -1.2, -0.8), armature.b_leg_l, this.PartLegBackLeftOBBMap);

        this.PartLegBackRightOBBMap.put(armature.b_thigh_r, new EntityOBBCollider(0.3, 0.5, 0.3, 0, 0, -0.1));
        this.PartLegBackRightOBBMap.put(armature.b_leg_r, new EntityOBBCollider(0.25, 0.5, 0.25, 0, 0.3, -0.1));
        this.PartLegBackRightOBBMap.put(armature.b_foot_r, new EntityOBBCollider(0.25, 0.5, 0.25, 0, 0, -0.1));
        this.leg_Back_Right = new CentaurKingPartEntity(this, 1.5F, 2.5F, new Vec3(0.0, -1.2, -0.8), armature.b_leg_r, this.PartLegBackRightOBBMap);


        this.subEntities = new CentaurKingPartEntity[]{
                this.head,
                this.chest,
                this.body,
                this.hand_Left,
                this.hand_Right,

                this.leg_Front_Left,
                this.leg_Front_Right,
                this.leg_Back_Left,
                this.leg_Back_Right,
        };
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            protected double getAttackReachSqr(@NotNull LivingEntity entity) {
                return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
            }
        });
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
    }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public double getMyRidingOffset() {
        return -0.35D;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(EpicFightUnHumanMob.MODID, "entity.generic.hurt"));
    }

    @Override
    public void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.ravager.step"))), 0.15f, 1);
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(EpicFightUnHumanMob.MODID, "entity.generic.death"));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 53.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ARMOR, 30.0)
                .add(Attributes.ARMOR_TOUGHNESS, 15.0)
                .add(Attributes.ATTACK_DAMAGE)
                .add(Attributes.FOLLOW_RANGE, 2048.0)
                .add(EpicFightAttributes.MAX_STRIKES.get(), 8.0)
                .add(EpicFightAttributes.IMPACT.get(), 6.0);
    }

    public static void init() {
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        // tips:手动tick所有部件,以此更新partEntity内OBBColider的位置
        for (PartEntity part : this.subEntities) {
            part.tick();
        }
    }

    @Override
    public @Nullable PartEntity<?>[] getParts() {
        return this.subEntities;
    }
}
