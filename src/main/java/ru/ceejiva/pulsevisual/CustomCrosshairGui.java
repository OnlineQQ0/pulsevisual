package ru.ceejiva.pulsevisual;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class CustomCrosshairGui extends LightweightGuiDescription {
    public static Screen createScreen() {
        return new Screen(Text.translatable("gui.pulsevisual.crosshair")) {
            private final CustomCrosshairGui gui = new CustomCrosshairGui();

            @Override
            protected void init() {
                gui.setRootPanel(new WGridPanel());
                this.init(this.client, this.width, this.height);
            }

            @Override
            public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
                this.renderBackground(context, mouseX, mouseY, delta);
                super.render(context, mouseX, mouseY, delta);
            }
        };
    }

    public CustomCrosshairGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);

        WLabel title = new WLabel(Text.translatable("gui.pulsevisual.crosshair"));
        root.add(title, 0, 0, 8, 1);

        WButton configButton = new WButton(Text.translatable("gui.pulsevisual.configure"));
        configButton.setOnClick(() -> Config.openConfigScreen("crosshair"));
        root.add(configButton, 1, 2, 6, 1);

        WButton editorButton = new WButton(Text.translatable("gui.pulsevisual.edit_crosshair"));
        editorButton.setOnClick(() -> MinecraftClient.getInstance().setScreen(CustomCrosshairEditorGui.createScreen()));
        root.add(editorButton, 1, 4, 6, 1);

        root.validate(this);
    }
}