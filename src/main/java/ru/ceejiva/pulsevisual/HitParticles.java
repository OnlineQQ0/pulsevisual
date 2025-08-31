package ru.ceejiva.pulsevisual;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.DustParticleEffect;

public class HitParticles {
    private static boolean wasAttacking = false;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && Config.enableHitParticles) {
                boolean isAttacking = client.options.attackKey.isPressed();

                // Проверяем, началась ли атака в этом тике
                if (isAttacking && !wasAttacking && client.targetedEntity != null) {
                    spawnHitParticles(client);
                }

                wasAttacking = isAttacking;
            }
        });
    }

    private static void spawnHitParticles(MinecraftClient client) {
        for (int i = 0; i < Config.particleCount; i++) {
            float red, green, blue;

            if (Config.enableRGBParticles) {
                red = ClientColor.getRgbColorComponent(System.currentTimeMillis(), 0);
                green = ClientColor.getRgbColorComponent(System.currentTimeMillis(), 120);
                blue = ClientColor.getRgbColorComponent(System.currentTimeMillis(), 240);
            } else {
                red = Config.particleColorRed;
                green = Config.particleColorGreen;
                blue = Config.particleColorBlue;
            }

            int particleColor = ((255 << 24) | ((int)(red * 255) << 16) | ((int)(green * 255) << 8) | (int)(blue * 255));

            double offsetX = (Math.random() - 0.5) * 0.5;
            double offsetY = (Math.random() - 0.5) * 0.5;
            double offsetZ = (Math.random() - 0.5) * 0.5;

            client.world.addParticle(
                    new DustParticleEffect(particleColor, 1.0f),
                    client.targetedEntity.getX() + offsetX,
                    client.targetedEntity.getY() + 1.0 + offsetY,
                    client.targetedEntity.getZ() + offsetZ,
                    0.0, 0.0, 0.0
            );
        }
    }
}