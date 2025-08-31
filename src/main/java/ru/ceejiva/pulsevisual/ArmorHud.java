package ru.ceejiva.pulsevisual;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class ArmorHud {
    public static void init() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null && Config.enableArmorHud) {
                int x = 5;
                int y = 5;
                drawContext.fill(x, y, x + 81, y + 9, ClientColor.getColor(Config.armorHudColorRed, Config.armorHudColorGreen, Config.armorHudColorBlue, Config.armorHudAlpha));
            }
        });
    }
}