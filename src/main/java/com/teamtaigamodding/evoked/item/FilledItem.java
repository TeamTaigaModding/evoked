package com.teamtaigamodding.evoked.item;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FilledItem extends Item {

    public static Item filledItem;

    public FilledItem(Properties properties, Item filledItem) {
        super(properties);
        this.filledItem = filledItem;
    }



    private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> filledItem);

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        FILLER.fillItem(this.asItem(), group, items);
    }
}
