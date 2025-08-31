package ru.ceejiva.pulsevisual.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.ceejiva.pulsevisual.Config;

@Mixin(PlayerEntity.class)
public class RemoveEffectsMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onPlayerTick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.getWorld().isClient) {
            if (Config.disableNausea) {
                player.removeStatusEffect(StatusEffects.NAUSEA);
            }
            if (Config.disableBlindness) {
                player.removeStatusEffect(StatusEffects.BLINDNESS);
            }
            if (Config.disableMiningFatigue) {
                player.removeStatusEffect(StatusEffects.MINING_FATIGUE);
            }
            if (Config.disableDarkness) {
                player.removeStatusEffect(StatusEffects.DARKNESS);
            }
        }
    }
}
