package ru.ceejiva.pulsevisual;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.DustParticleEffect;

public class Effectify {
    public static void init() {
        WorldRenderEvents.AFTER_ENTITIES.register((context) -> {
            if (Config.trailEnabled && MinecraftClient.getInstance().player != null) {
                MinecraftClient client = MinecraftClient.getInstance();

                float red, green, blue;
                if (Config.rgbEnabled) {
                    red = ClientColor.getRgbColorComponent(System.currentTimeMillis(), 0);
                    green = ClientColor.getRgbColorComponent(System.currentTimeMillis(), 120);
                    blue = ClientColor.getRgbColorComponent(System.currentTimeMillis(), 240);
                } else {
                    red = Config.trailColorRed;
                    green = Config.trailColorGreen;
                    blue = Config.trailColorBlue;
                }

                // Создаем int цвет для DustParticleEffect (ARGB формат)
                int particleColor = ((255 << 24) | ((int)(red * 255) << 16) | ((int)(green * 255) << 8) | (int)(blue * 255));

                context.world().addParticle(
                        new DustParticleEffect(particleColor, 1.0f),
                        client.player.getX(),
                        client.player.getY() + 1.0,
                        client.player.getZ(),
                        0.0, 0.0, 0.0
                );
            }
        });
    }
}