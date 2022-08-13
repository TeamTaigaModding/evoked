package com.teamtaigamodding.evoked;

import com.teamtaigamodding.evoked.item.FilledItem;
import com.teamtaigamodding.evoked.item.TotemHuskItem;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.CreativeModeTab;

public class EvokedItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Evoked.MOD_ID);

    public static final RegistryObject<Item> RUNE_OF_LIFE = ITEMS.register("rune_of_life", () -> new FilledItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC), Items.PRISMARINE_CRYSTALS));
    public static final RegistryObject<Item> TOTEM_HUSK = ITEMS.register("totem_husk", () -> new TotemHuskItem(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_COMBAT)));
}
