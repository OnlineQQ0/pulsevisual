package ru.ceejiva.pulsevisual;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.RenderLayer;

public class CustomCrosshair {
    private static final Identifier[] CROSSHAIRS = {
            Identifier.of(PulseVisualMod.MOD_ID, "textures/crosshair1.png"),
            Identifier.of(PulseVisualMod.MOD_ID, "textures/crosshair2.png")
    };

    public static void init() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (Config.enableCustomCrosshair) {
                renderCrosshair(drawContext);
            }
        });
    }

    private static void renderCrosshair(DrawContext drawContext) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();
        int x = screenWidth / 2;
        int y = screenHeight / 2;

        int color = Config.crosshairOnTarget && client.targetedEntity != null ?
                ClientColor.getColor(1.0f, 0.0f, 0.0f, Config.crosshairAlpha) :
                ClientColor.getColor(Config.crosshairColorRed, Config.crosshairColorGreen, Config.crosshairColorBlue, Config.crosshairAlpha);

        if (Config.useCustomCrosshairShape) {
            // Рендеринг пиксельного прицела
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if (Config.crosshairPixels[i][j]) {
                        drawContext.fill(x + i - 8, y + j - 8, x + i - 7, y + j - 7, color);
                    }
                }
            }
        } else {
            // Рендеринг текстурного прицела
            if (Config.crosshairType < CROSSHAIRS.length) {
                Identifier texture = CROSSHAIRS[Config.crosshairType];
                try {
                    drawContext.drawTexture(RenderLayer::getGuiTextured, texture,
                            x - Config.crosshairSize / 2, y - Config.crosshairSize / 2,
                            0.0f, 0.0f, Config.crosshairSize, Config.crosshairSize,
                            Config.crosshairSize, Config.crosshairSize);
                } catch (Exception e) {
                    // Fallback: рендерим простой крест
                    renderSimpleCrosshair(drawContext, x, y, color);
                }
            } else {
                // Fallback: рендерим простой крест
                renderSimpleCrosshair(drawContext, x, y, color);
            }
        }
    }

    private static void renderSimpleCrosshair(DrawContext drawContext, int x, int y, int color) {
        int size = Config.crosshairSize / 4;
        // Горизонтальная линия
        drawContext.fill(x - size, y, x + size, y + 1, color);
        // Вертикальная линия
        drawContext.fill(x, y - size, x + 1, y + size, color);
    }
}