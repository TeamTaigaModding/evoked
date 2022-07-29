package com.teamtaigamodding.evoked.scheduler;

import com.teamtaigamodding.evoked.EvokedItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.concurrent.TimeUnit;

public class GiveTotemTask implements Runnable{

    private final LivingEntity LE;

    public GiveTotemTask(LivingEntity le) {
        LE = le;
    }

    @Override
    public void run() {
        ItemStack TotemHusk = new ItemStack(EvokedItems.TOTEM_HUSK.get());

        if (LE.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            LE.setItemInHand(InteractionHand.MAIN_HAND, TotemHusk);
        }
        else if (LE.getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
            LE.setItemInHand(InteractionHand.OFF_HAND, TotemHusk);
        }
        else if (LE instanceof Player player) {
            if (!player.getInventory().add(TotemHusk)) {
                player.drop(TotemHusk, false);
            }
        }

    }
}
