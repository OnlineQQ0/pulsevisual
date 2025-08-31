package ru.ceejiva.pulsevisual;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class CustomCrosshairEditorGui extends LightweightGuiDescription {
    public static Screen createScreen() {
        return new Screen(Text.translatable("gui.pulsevisual.edit_crosshair")) {
            private final CustomCrosshairEditorGui gui = new CustomCrosshairEditorGui();

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

    public CustomCrosshairEditorGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);

        WLabel title = new WLabel(Text.translatable("gui.pulsevisual.edit_crosshair"));
        root.add(title, 0, 0, 8, 1);

        WButton saveButton = new WButton(Text.translatable("gui.pulsevisual.save"));
        saveButton.setOnClick(() -> MinecraftClient.getInstance().setScreen(CustomCrosshairGui.createScreen()));
        root.add(saveButton, 1, 2, 6, 1);

        root.validate(this);
    }
}