package ru.ceejiva.pulsevisual.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class TotemMixin {
    // Временное отключение инъекции, так как метод не найден
    // @Inject(method = "tryUseTotem(Lnet/minecraft/entity/damage/DamageSource;)Z", at = @At("HEAD"))
    // private void onTryUseTotem(net.minecraft.entity.damage.DamageSource source, CallbackInfoReturnable<Boolean> cir) {
    //     if (source.getAttacker() instanceof LivingEntity attacker && attacker.getActiveItem().getItem() == Items.TOTEM_OF_UNDYING) {
    //         // Дополнительная логика при активации тотема
    //     }
    // }

    // Альтернативный подход: проверка в applyDamage
    @Inject(method = "applyDamage", at = @At("HEAD"))
    private void onApplyDamage(ServerWorld world, DamageSource source, float amount, CallbackInfo ci) {
        if (source.getAttacker() instanceof LivingEntity attacker && attacker.getActiveItem().getItem() == Items.TOTEM_OF_UNDYING) {
            // Логика при активации тотема
        }
    }
}