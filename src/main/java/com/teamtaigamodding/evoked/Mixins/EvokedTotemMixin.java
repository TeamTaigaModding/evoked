package com.teamtaigamodding.evoked.Mixins;

import com.teamtaigamodding.evoked.scheduler.EvokedTickHandler;
import com.teamtaigamodding.evoked.scheduler.GiveTotemTask;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import com.teamtaigamodding.evoked.EvokedConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.TimeUnit;

@Mixin(LivingEntity.class)

// Mixins totems to become totem husks when they are destroyed

public abstract class EvokedTotemMixin extends Entity {

    protected EvokedTotemMixin(EntityType<? extends LivingEntity> EntityType, Level Level) {
        super(EntityType, Level);
    }

    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/world/entity/LivingEntity;checkTotemDeathProtection(Lnet/minecraft/world/damagesource/DamageSource;)Z", cancellable = true)
    private void CheckTotemDeathProtection(DamageSource DamageSource, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity LE = (LivingEntity) (Object) this;
        boolean isReal = LE.getEffect(MobEffects.ABSORPTION).getAmplifier() == 1
                && LE.getEffect(MobEffects.DAMAGE_RESISTANCE).getAmplifier() == 1
                && LE.getEffect(MobEffects.FIRE_RESISTANCE).getAmplifier() == 0
                && LE.getEffect(MobEffects.ABSORPTION).getDuration() == 100 &&
                LE.getEffect(MobEffects.FIRE_RESISTANCE).getDuration()  == 800;
        if (LE.getHealth() == 1.0f && isReal) EvokedTickHandler.scheduleAsyncTask(new GiveTotemTask(LE), 2130, TimeUnit.MILLISECONDS);
    }

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/world/entity/LivingEntity;checkTotemDeathProtection(Lnet/minecraft/world/damagesource/DamageSource;)Z", cancellable = true)
    private void CheckTotemDeathProtectionH(DamageSource DamageSource, CallbackInfoReturnable<Boolean> cir) {
        if (EvokedConfig.Common.COMMON.TotemsWorkInVoid.get()) {
            LivingEntity LE = (LivingEntity) (Object) this;
            if (LE.getY() < -64 && DamageSource.equals(DamageSource.OUT_OF_WORLD)) {
                ItemStack itemstack = null;
                for (InteractionHand interactionhand : InteractionHand.values()) {
                    ItemStack itemstack1 = LE.getItemInHand(interactionhand);
                    if (itemstack1.is(Items.TOTEM_OF_UNDYING)) {
                        itemstack = itemstack1.copy();
                        itemstack1.shrink(1);

                        break;
                    }
                }
                if (itemstack != null) {
                    if (LE instanceof ServerPlayer) {
                        ServerPlayer serverplayer = (ServerPlayer) LE;
                        serverplayer.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING), 1);
                        CriteriaTriggers.USED_TOTEM.trigger(serverplayer, itemstack);
                    }
                    LE.setHealth(1.0F);
                    LE.removeAllEffects();
                    LE.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 300, 0));
                    LE.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
                    LE.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
                    LE.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));

                    LE.teleportTo(LE.getX(), 160, LE.getZ());
                    LE.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 0));


                    EvokedTickHandler.scheduleAsyncTask(new GiveTotemTask(LE), 2130, TimeUnit.MILLISECONDS);
                    LE.level.broadcastEntityEvent(this, (byte) 35);
                    cir.setReturnValue(true);
                }

            }
        }
    }
}
