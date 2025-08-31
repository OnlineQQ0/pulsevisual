package ru.ceejiva.pulsevisual.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class RemoveEffectsMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void onPlayerTick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player.getWorld().isClient) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null && client.player.equals(player)) {
                if (ru.ceejiva.pulsevisual.Config.disableNausea) {
                    player.removeStatusEffect(net.minecraft.entity.effect.StatusEffects.NAUSEA);
                }
                if (ru.ceejiva.pulsevisual.Config.disableBlindness) {
                    player.removeStatusEffect(net.minecraft.entity.effect.StatusEffects.BLINDNESS);
                }
                if (ru.ceejiva.pulsevisual.Config.disableMiningFatigue) {
                    player.removeStatusEffect(net.minecraft.entity.effect.StatusEffects.MINING_FATIGUE);
                }
                if (ru.ceejiva.pulsevisual.Config.disableDarkness) {
                    player.removeStatusEffect(net.minecraft.entity.effect.StatusEffects.DARKNESS);
                }
            }
        }
    }
}