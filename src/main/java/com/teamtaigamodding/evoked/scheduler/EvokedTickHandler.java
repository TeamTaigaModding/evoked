package com.teamtaigamodding.evoked.scheduler;

import com.google.common.collect.HashMultimap;

import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;

// Credit: Thank you Tslat for the code

public class EvokedTickHandler {
    private static ScheduledExecutorService scheduler = null;
    public static HashSet<Runnable> REQUIRED_TASKS = new HashSet<Runnable>();
    public static HashMultimap<Integer, Runnable> TICK_HANDLER = HashMultimap.<Integer, Runnable>create();

    public static void scheduleAsyncTask(Runnable run, int time, TimeUnit unit) {
        if (scheduler == null) scheduler = Executors.newScheduledThreadPool(1);

        scheduler.schedule(run, time, unit);
    }

    public static void handleSyncScheduledTasks(int tick) {
        if (TICK_HANDLER.containsKey(tick)) {
            Iterator<Runnable> tasks = TICK_HANDLER.get(tick).iterator();

            while (tasks.hasNext()) {
                tasks.next().run();
                tasks.remove();
            }
        }
    }
}
