package com.teamtaigamodding.evoked.Mixins;

import com.teamtaigamodding.evoked.EvokedItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
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

import com.teamtaigamodding.evoked.EvokedItems;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)

// Mixins totems to become totem husks when they are destroyed

public abstract class EvokedTotemMixin extends Entity {

    protected EvokedTotemMixin(EntityType<? extends LivingEntity> EntityType, Level Level) {
        super(EntityType, Level);
    }

    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/world/entity/LivingEntity;checkTotemDeathProtection(Lnet/minecraft/world/damagesource/DamageSource;)Z", cancellable = true)
    private void EvokedTotemMixin(DamageSource DamageSource, CallbackInfoReturnable<Boolean> cir) {
        if (((LivingEntity) (Object) this).getHealth() == 1.0F) {
            for (InteractionHand IH : InteractionHand.values()) {
                ItemStack TotemHusk = new ItemStack(EvokedItems.TOTEM_HUSK.get());
                if ( ((LivingEntity) (Object) this).getItemInHand(IH).isEmpty()) {

                    ((LivingEntity) (Object) this).setItemInHand(IH, TotemHusk);
                    break;
                }
            }
        }
    }
}
