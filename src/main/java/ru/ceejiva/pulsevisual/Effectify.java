package ru.ceejiva.pulsevisual;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.DustParticleEffect;

public class Effectify {
    public static void init() {
        WorldRenderEvents.AFTER_ENTITIES.register((context) -> {
            if (Config.trailEnabled && MinecraftClient.getInstance().player != null) {
                float red = Config.rgbEnabled ? ClientColor.getRgbColorComponent(System.currentTimeMillis(), 0) : Config.trailColorRed;
                float green = Config.rgbEnabled ? ClientColor.getRgbColorComponent(System.currentTimeMillis(), 120) : Config.trailColorGreen;
                float blue = Config.rgbEnabled ? ClientColor.getRgbColorComponent(System.currentTimeMillis(), 240) : Config.trailColorBlue;
                int color = ((255 << 24) | ((int)(red * 255) << 16) | ((int)(green * 255) << 8) | (int)(blue * 255)); // ARGB
                context.world().addParticle(
                        new DustParticleEffect(color, 1.0f), // Используем int для цвета и float для размера
                        MinecraftClient.getInstance().player.getX(),
                        MinecraftClient.getInstance().player.getY(),
                        MinecraftClient.getInstance().player.getZ(),
                        0.0, 0.0, 0.0
                );
            }
        });
    }
}