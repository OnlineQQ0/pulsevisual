package ru.ceejiva.pulsevisual.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.ceejiva.pulsevisual.Config;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Inject(method = "setupTerrain", at = @At("HEAD"), cancellable = true)
    private void setupTerrain(Camera camera, net.minecraft.client.render.Frustum frustum, boolean hasForcedFrustum, CallbackInfo ci) {
        if (Config.seeThroughBlocks) {
            ci.cancel(); // Требуется доработка для корректного рендера
        }
    }
}