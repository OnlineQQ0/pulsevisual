package ru.ceejiva.pulsevisual;

import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class PulseVisualGui extends LightweightGuiDescription {
    public static Screen createScreen() {
        return new CottonClientScreen(new PulseVisualGui());
    }

    public PulseVisualGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);

        // Заголовок
        WLabel title = new WLabel(Text.literal("Pulse Visual"));
        root.add(title, 0, 0, 8, 1);

        // Кнопка для открытия основной конфигурации
        WButton configButton = new WButton(Text.literal("Main Config"));
        configButton.setOnClick(() -> {
            MinecraftClient.getInstance().setScreen(Config.configScreen);
        });
        root.add(configButton, 1, 2, 6, 1);

        // Кнопка для редактора прицела
        WButton crosshairEditorButton = new WButton(Text.literal("Crosshair Editor"));
        crosshairEditorButton.setOnClick(() -> {
            MinecraftClient.getInstance().setScreen(CustomCrosshairGui.createScreen());
        });
        root.add(crosshairEditorButton, 1, 4, 6, 1);

        // Кнопка закрытия
        WButton backButton = new WButton(Text.literal("Close"));
        backButton.setOnClick(() -> {
            MinecraftClient.getInstance().setScreen(null);
        });
        root.add(backButton, 1, 6, 6, 1);

        root.validate(this);
    }
}