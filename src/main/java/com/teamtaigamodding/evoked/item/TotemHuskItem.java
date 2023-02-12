package com.teamtaigamodding.evoked.item;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import com.teamtaigamodding.evoked.EvokedItems;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TotemHuskItem extends Item {
    public TotemHuskItem(Properties properties) {
        super(properties);
    }

    private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> Items.SHIELD);

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack huskItem, ItemStack clickItem, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess) {
        if (clickAction == ClickAction.SECONDARY && slot.allowModification(player)) {
            if (clickItem.is(EvokedItems.RUNE_OF_LIFE.get())) {
                slot.set(new ItemStack(Items.TOTEM_OF_UNDYING));
                clickItem.shrink(1);
                playInsertSound(player);
            }
            return true;
        }

        return false;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        FILLER.fillItem(this.asItem(), group, items);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.RESPAWN_ANCHOR_CHARGE, 0.8F, 0.8F + entity.getLevel().getRandom().nextFloat() * 0.4F);
    }
}
