package ru.ceejiva.pulsevisual;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;

public class RemoveEffects {
    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                if (Config.disableNausea) {
                    client.player.removeStatusEffect(net.minecraft.entity.effect.StatusEffects.NAUSEA);
                }
                if (Config.disableBlindness) {
                    client.player.removeStatusEffect(net.minecraft.entity.effect.StatusEffects.BLINDNESS);
                }
                if (Config.disableMiningFatigue) {
                    client.player.removeStatusEffect(net.minecraft.entity.effect.StatusEffects.MINING_FATIGUE);
                }
                if (Config.disableDarkness) {
                    client.player.removeStatusEffect(net.minecraft.entity.effect.StatusEffects.DARKNESS);
                }
            }
        });
    }
}