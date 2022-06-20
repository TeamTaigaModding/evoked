package com.teamtaigamodding.evoked;

import net.minecraft.world.item.*;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.CreativeModeTab;
import com.teamtaigamodding.evoked.Evoked;

public class EvokedItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Evoked.MOD_ID);

    public static final RegistryObject<Item> RUNE_OF_LIFE = ITEMS.register("rune_of_life", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> TOTEM_HUSK = ITEMS.register("totem_husk", () -> new Item(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_COMBAT)));
}
