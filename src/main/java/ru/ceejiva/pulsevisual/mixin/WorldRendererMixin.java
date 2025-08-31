package ru.ceejiva.pulsevisual.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import ru.ceejiva.pulsevisual.Config;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    // Безопасная реализация "видения сквозь блоки"
    // Вместо отмены рендера, изменяем прозрачность блоков
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderBlock(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;ZJ)Z"), index = 5)
    private boolean modifyBlockTransparency(boolean original) {
        if (Config.seeThroughBlocks) {
            return true; // Делаем блоки полупрозрачными
        }
        return original;
    }
}