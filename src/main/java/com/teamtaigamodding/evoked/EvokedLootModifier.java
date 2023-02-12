package com.teamtaigamodding.evoked;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EvokedLootModifier {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, Evoked.MOD_ID);

    public static final RegistryObject<GlobalLootModifierSerializer<?>> REMOVE_ITEM = LOOT_MODIFIERS.register("remove_item", RemoveOneItemModifier.Serializer::new);
}
