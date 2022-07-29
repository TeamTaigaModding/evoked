package com.teamtaigamodding.evoked.scheduler;

import net.minecraftforge.event.TickEvent;

public class EvokedTickEvent {
    public static int tick;

    public static void serverTickEvent(final TickEvent.ServerTickEvent Event) {
        if (Event.phase == TickEvent.Phase.END) {
            tick++;

            EvokedTickHandler.handleSyncScheduledTasks(tick);
        }
    }
}