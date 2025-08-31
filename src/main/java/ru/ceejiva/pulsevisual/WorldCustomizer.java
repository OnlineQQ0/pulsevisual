package ru.ceejiva.pulsevisual;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class WorldCustomizer {
    public static void init() {
        WorldRenderEvents.BEFORE_ENTITIES.register((context) -> {
            if (Config.customTimeEnabled) {
                context.world().getLevelProperties().setTimeOfDay(Config.customTime);
            }
        });
    }
}