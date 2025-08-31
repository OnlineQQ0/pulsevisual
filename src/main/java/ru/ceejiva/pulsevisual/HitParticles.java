package ru.ceejiva.pulsevisual;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.DustParticleEffect;

public class HitParticles {
    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.player.getAttackCooldownProgress(0.0f) == 1.0f && client.options.attackKey.isPressed()) {
                if (client.targetedEntity != null) {
                    for (int i = 0; i < Config.particleCount; i++) {
                        float red = Config.rgbEnabled ? ClientColor.getRgbColorComponent(System.currentTimeMillis(), 0) : Config.particleColorRed;
                        float green = Config.rgbEnabled ? ClientColor.getRgbColorComponent(System.currentTimeMillis(), 120) : Config.particleColorGreen;
                        float blue = Config.rgbEnabled ? ClientColor.getRgbColorComponent(System.currentTimeMillis(), 240) : Config.particleColorBlue;
                        int color = ((255 << 24) | ((int)(red * 255) << 16) | ((int)(green * 255) << 8) | (int)(blue * 255)); // ARGB
                        client.world.addParticle(
                                new DustParticleEffect(color, 1.0f), // Используем int для цвета и float для размера
                                client.targetedEntity.getX() + (Math.random() - 0.5) * 0.5,
                                client.targetedEntity.getY() + 1.0 + (Math.random() - 0.5) * 0.5,
                                client.targetedEntity.getZ() + (Math.random() - 0.5) * 0.5,
                                0.0, 0.0, 0.0
                        );
                    }
                }
            }
        });
    }
}