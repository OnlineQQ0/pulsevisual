package ru.ceejiva.pulsevisual.mixin;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.ceejiva.pulsevisual.Config;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "render(Lnet/minecraft/client/render/RenderTickCounter;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;render(Lnet/minecraft/client/render/RenderTickCounter;Z)V"), cancellable = true)
    private void cancelBlindness(net.minecraft.client.render.RenderTickCounter tickCounter, boolean tick, CallbackInfo ci) {
        if (Config.disableBlindness) ci.cancel();
    }
}