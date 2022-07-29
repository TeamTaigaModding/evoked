package com.teamtaigamodding.evoked;

import com.mojang.logging.LogUtils;
import com.teamtaigamodding.evoked.scheduler.EvokedTickEvent;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Evoked.MOD_ID)
public class Evoked {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "evoked";

    public Evoked() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::loadEvents);


        EvokedItems.ITEMS.register(modEventBus);


        MinecraftForge.EVENT_BUS.register(this);
    }
    private void loadEvents(final FMLLoadCompleteEvent event) {
        MinecraftForge.EVENT_BUS.register(new EvokedTickEvent());
    }
    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
