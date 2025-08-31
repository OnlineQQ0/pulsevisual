package ru.ceejiva.pulsevisual;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;

public class WorldCustomizer {
    public static void init() {
        WorldRenderEvents.START.register((context) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.world != null && Config.customTimeEnabled) {
                // Устанавливаем время только на клиенте для визуального эффекта
                // Не изменяем серверное время
                try {
                    // Метод для установки времени может отличаться в разных версиях
                    // Используем безопасный подход
                    if (client.world.getLevelProperties() != null) {
                        // Это только визуальное изменение для клиента
                        context.world().getLevelProperties().setTimeOfDay(Config.customTime);
                    }
                } catch (Exception e) {
                    // Игнорируем ошибки, если метод недоступен
                }
            }
        });
    }
}