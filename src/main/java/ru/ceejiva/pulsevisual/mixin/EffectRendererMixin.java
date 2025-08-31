package ru.ceejiva.pulsevisual.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.ceejiva.pulsevisual.Config;

@Mixin(InGameOverlayRenderer.class)
public class EffectRendererMixin {
    @Inject(method = "renderOverlays", at = @At("HEAD"), cancellable = true)
    private static void cancelNausea(MinecraftClient client, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        if (Config.disableNausea) {
            ci.cancel();
        }
    }
}