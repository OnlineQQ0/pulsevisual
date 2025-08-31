package ru.ceejiva.pulsevisual;

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
        Config.init(); // Инициализируем конфигурацию
        PulseVisualGui gui = new PulseVisualGui();
        return new Screen(Text.translatable("gui.pulsevisual.title")) {
            @Override
            protected void init() {
                // Cotton GUI управляет фокусом автоматически, setFocused не требуется
            }

            @Override
            public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
                // Cotton GUI обрабатывает рендеринг, не вызываем renderBackground
                super.render(context, mouseX, mouseY, delta);
            }
        };
    }

    public PulseVisualGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);

        // Заголовок
        WLabel title = new WLabel(Text.translatable("gui.pulsevisual.title"));
        root.add(title, 0, 0, 8, 1);

        // Кнопка для конфигурации прицела
        WButton crosshairButton = new WButton(Text.translatable("gui.pulsevisual.crosshair"));
        crosshairButton.setOnClick(() -> Config.openConfigScreen("crosshair"));
        root.add(crosshairButton, 1, 2, 6, 1);

        // Кнопка для конфигурации частиц при ударе
        WButton particlesButton = new WButton(Text.translatable("gui.pulsevisual.particles"));
        particlesButton.setOnClick(() -> Config.openConfigScreen("particles"));
        root.add(particlesButton, 1, 4, 6, 1);

        // Кнопка для конфигурации эффектов
        WButton effectsButton = new WButton(Text.translatable("gui.pulsevisual.effects"));
        effectsButton.setOnClick(() -> Config.openConfigScreen("effects"));
        root.add(effectsButton, 1, 6, 6, 1);

        // Кнопка для конфигурации мира
        WButton worldButton = new WButton(Text.translatable("gui.pulsevisual.world"));
        worldButton.setOnClick(() -> Config.openConfigScreen("world"));
        root.add(worldButton, 1, 8, 6, 1);

        // Кнопка для конфигурации трейлов
        WButton trailButton = new WButton(Text.translatable("gui.pulsevisual.trail"));
        trailButton.setOnClick(() -> Config.openConfigScreen("trail"));
        root.add(trailButton, 1, 10, 6, 1);

        // Кнопка закрытия
        WButton backButton = new WButton(Text.translatable("gui.back"));
        backButton.setOnClick(() -> MinecraftClient.getInstance().setScreen(null));
        root.add(backButton, 1, 12, 6, 1);

        root.validate(this);
    }
}