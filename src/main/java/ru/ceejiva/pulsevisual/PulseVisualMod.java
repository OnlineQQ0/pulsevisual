package ru.ceejiva.pulsevisual;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import ru.ceejiva.pulsevisual.mixin.TotemMixin; // Добавьте импорт, если миксин используется

@Environment(EnvType.CLIENT)
public class PulseVisualMod implements ClientModInitializer {
    public static final String MOD_ID = "pulsevisual";
    public static KeyBinding openGuiKey;

    @Override
    public void onInitializeClient() {
        Config.init();
        CustomCrosshair.init();
        HitParticles.init();
        Effectify.init();
        RemoveEffects.init();
        WorldCustomizer.init();
        ArmorHud.init();
        PotionsHud.init();
        TargetHud.init();

        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.pulsevisual.open_gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.pulsevisual"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.wasPressed()) {
                client.setScreen(PulseVisualGui.createScreen());
            }
        });
    }
}