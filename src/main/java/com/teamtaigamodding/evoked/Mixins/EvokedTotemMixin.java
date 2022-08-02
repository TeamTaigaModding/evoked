package com.teamtaigamodding.evoked.Mixins;

import com.teamtaigamodding.evoked.scheduler.EvokedTickHandler;
import com.teamtaigamodding.evoked.scheduler.GiveTotemTask;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
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
    private void EvokedTotemMixin(DamageSource DamageSource, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity LE = (LivingEntity) (Object) this;
        if (LE.getHealth() == 1.0f) EvokedTickHandler.scheduleAsyncTask(new GiveTotemTask(LE), 2130, TimeUnit.MILLISECONDS);
    }
}
