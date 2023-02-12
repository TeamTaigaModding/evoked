package com.teamtaigamodding.evoked;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RemoveOneItemModifier extends LootModifier {

    private final Item toRemove;

    protected RemoveOneItemModifier(Item toRemove, LootItemCondition[] conditionsIn) {
        super(conditionsIn);
        this.toRemove = toRemove;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        for (int i = 0; i < generatedLoot.size();i++) {
            if (generatedLoot.get(i).getItem().equals(toRemove)) {
                generatedLoot.remove(i);
            }
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<RemoveOneItemModifier> {
        @Override
        public RemoveOneItemModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            Item removeItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object, "item"))));
            return new RemoveOneItemModifier(removeItem, ailootcondition);
        }

        @Override
        public JsonObject write(RemoveOneItemModifier instance) {
            return new JsonObject();
        }
    }
}
