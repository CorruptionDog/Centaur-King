package com.corruptdog.efat.entity.type;
import com.corruptdog.efat.main.EpicFightUnHumanMob;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

import java.util.Objects;


public class CentaurKing extends Monster {


    public CentaurKing(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
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
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(EpicFightUnHumanMob.MODID,"entity.generic.hurt"));
    }
    @Override
    public void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
        this.playSound(Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.ravager.step"))), 0.15f, 1);
    }
    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(EpicFightUnHumanMob.MODID,"entity.generic.death"));
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

}
