package ru.ceejiva.pulsevisual;

import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class CustomCrosshairEditorGui extends LightweightGuiDescription {
    private static boolean[][] editingPixels = new boolean[16][16];

    static {
        // Копируем текущие настройки
        for (int i = 0; i < 16; i++) {
            System.arraycopy(Config.crosshairPixels[i], 0, editingPixels[i], 0, 16);
        }
    }

    public static Screen createScreen() {
        return new CottonClientScreen(new CustomCrosshairEditorGui()) {
            @Override
            public void render(DrawContext context, int mouseX, int mouseY, float delta) {
                super.render(context, mouseX, mouseY, delta);

                // Рендерим редактор пикселей
                int startX = this.width / 2 - 128;
                int startY = this.height / 2 - 128;

                for (int i = 0; i < 16; i++) {
                    for (int j = 0; j < 16; j++) {
                        int pixelX = startX + i * 16;
                        int pixelY = startY + j * 16;

                        int color = editingPixels[i][j] ? 0xFFFFFFFF : 0xFF808080;
                        context.fill(pixelX, pixelY, pixelX + 15, pixelY + 15, color);

                        // Рамка
                        context.drawBorder(pixelX, pixelY, 15, 15, 0xFF000000);
                    }
                }
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                int startX = this.width / 2 - 128;
                int startY = this.height / 2 - 128;

                if (mouseX >= startX && mouseX < startX + 256 && mouseY >= startY && mouseY < startY + 256) {
                    int pixelI = (int)((mouseX - startX) / 16);
                    int pixelJ = (int)((mouseY - startY) / 16);

                    if (pixelI >= 0 && pixelI < 16 && pixelJ >= 0 && pixelJ < 16) {
                        editingPixels[pixelI][pixelJ] = !editingPixels[pixelI][pixelJ];
                        return true;
                    }
                }

                return super.mouseClicked(mouseX, mouseY, button);
            }
        };
    }

    public CustomCrosshairEditorGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);

        WLabel title = new WLabel(Text.literal("Crosshair Pixel Editor"));
        root.add(title, 0, 0, 8, 1);

        WLabel instructions = new WLabel(Text.literal("Click pixels to toggle"));
        root.add(instructions, 0, 1, 8, 1);

        WButton saveButton = new WButton(Text.literal("Save"));
        saveButton.setOnClick(() -> {
            // Сохраняем изменения
            for (int i = 0; i < 16; i++) {
                System.arraycopy(editingPixels[i], 0, Config.crosshairPixels[i], 0, 16);
            }
            Config.useCustomCrosshairShape = true;
            MinecraftClient.getInstance().setScreen(null);
        });
        root.add(saveButton, 1, 15, 2, 1);

        WButton clearButton = new WButton(Text.literal("Clear"));
        clearButton.setOnClick(() -> {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    editingPixels[i][j] = false;
                }
            }
        });
        root.add(clearButton, 4, 15, 2, 1);

        WButton cancelButton = new WButton(Text.literal("Cancel"));
        cancelButton.setOnClick(() -> {
            MinecraftClient.getInstance().setScreen(null);
        });
        root.add(cancelButton, 7, 15, 2, 1);

        root.validate(this);
    }
}