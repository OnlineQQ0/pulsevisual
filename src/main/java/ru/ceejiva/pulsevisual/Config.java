package ru.ceejiva.pulsevisual;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class Config {
    // Настройки для отключения эффектов
    public static boolean disableNausea = true;
    public static boolean disableBlindness = true;
    public static boolean disableMiningFatigue = true;
    public static boolean disableDarkness = true;

    // Настройки для прицела (CustomCrosshair)
    public static boolean enableCustomCrosshair = true;
    public static int crosshairSize = 16;
    public static boolean crosshairOnTarget = true;
    public static float crosshairAlpha = 1.0f;
    public static float crosshairColorRed = 1.0f;
    public static float crosshairColorGreen = 1.0f;
    public static float crosshairColorBlue = 1.0f;
    public static boolean useCustomCrosshairShape = false;
    public static boolean[][] crosshairPixels = new boolean[16][16];
    public static int crosshairType = 0;

    // Глобальная скорость RGB для всех эффектов
    public static float rgbSpeed = 1.0f;

    // Настройки для частиц при ударе (HitParticles)
    public static boolean enableHitParticles = true;
    public static int particleCount = 5;
    public static boolean enableRGBParticles = false;
    public static float particleColorRed = 1.0f;
    public static float particleColorGreen = 0.0f;
    public static float particleColorBlue = 0.0f;

    // Настройки HUD
    public static boolean enableArmorHud = true;
    public static float armorHudColorRed = 1.0f;
    public static float armorHudColorGreen = 1.0f;
    public static float armorHudColorBlue = 1.0f;
    public static float armorHudAlpha = 1.0f;

    public static boolean enablePotionsHud = true;
    public static float potionsHudColorRed = 0.0f;
    public static float potionsHudColorGreen = 1.0f;
    public static float potionsHudColorBlue = 0.0f;
    public static float potionsHudAlpha = 1.0f;

    public static boolean enableTargetHud = true;
    public static float targetHudColorRed = 1.0f;
    public static float targetHudColorGreen = 0.0f;
    public static float targetHudColorBlue = 0.0f;
    public static float targetHudAlpha = 1.0f;

    // Настройки эффектов (Effectify)
    public static boolean trailEnabled = true;
    public static boolean rgbEnabled = false;
    public static float trailColorRed = 1.0f;
    public static float trailColorGreen = 1.0f;
    public static float trailColorBlue = 1.0f;

    // Настройки видимости сквозь блоки
    public static boolean seeThroughBlocks = true;

    // Настройки времени суток и неба (WorldCustomizer)
    public static boolean customTimeEnabled = true;
    public static int customTime = 6000;
    public static float skyTintRed = 0.5f;
    public static float skyTintGreen = 0.5f;
    public static float skyTintBlue = 1.0f;

    // Ссылка на экран конфигурации
    public static Screen configScreen;

    public static void init() {
        ConfigBuilder builder = ConfigBuilder.create()
                .setTitle(Text.literal("Pulse Visual Config"))
                .setSavingRunnable(() -> {});

        ConfigCategory general = builder.getOrCreateCategory(Text.literal("General"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // Категория отключения эффектов
        general.addEntry(entryBuilder.startBooleanToggle(Text.literal("Disable Nausea"), disableNausea)
                .setSaveConsumer(newValue -> disableNausea = newValue)
                .build());
        general.addEntry(entryBuilder.startBooleanToggle(Text.literal("Disable Blindness"), disableBlindness)
                .setSaveConsumer(newValue -> disableBlindness = newValue)
                .build());
        general.addEntry(entryBuilder.startBooleanToggle(Text.literal("Disable Mining Fatigue"), disableMiningFatigue)
                .setSaveConsumer(newValue -> disableMiningFatigue = newValue)
                .build());
        general.addEntry(entryBuilder.startBooleanToggle(Text.literal("Disable Darkness"), disableDarkness)
                .setSaveConsumer(newValue -> disableDarkness = newValue)
                .build());

        // Категория прицела
        ConfigCategory crosshair = builder.getOrCreateCategory(Text.literal("Crosshair"));
        crosshair.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable Custom Crosshair"), enableCustomCrosshair)
                .setSaveConsumer(newValue -> enableCustomCrosshair = newValue)
                .build());
        crosshair.addEntry(entryBuilder.startIntField(Text.literal("Crosshair Size"), crosshairSize)
                .setMin(4).setMax(32)
                .setSaveConsumer(newValue -> crosshairSize = newValue)
                .build());
        crosshair.addEntry(entryBuilder.startBooleanToggle(Text.literal("Crosshair On Target"), crosshairOnTarget)
                .setSaveConsumer(newValue -> crosshairOnTarget = newValue)
                .build());
        crosshair.addEntry(entryBuilder.startFloatField(Text.literal("Crosshair Alpha"), crosshairAlpha)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> crosshairAlpha = newValue)
                .build());
        crosshair.addEntry(entryBuilder.startFloatField(Text.literal("Crosshair Color Red"), crosshairColorRed)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> crosshairColorRed = newValue)
                .build());
        crosshair.addEntry(entryBuilder.startFloatField(Text.literal("Crosshair Color Green"), crosshairColorGreen)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> crosshairColorGreen = newValue)
                .build());
        crosshair.addEntry(entryBuilder.startFloatField(Text.literal("Crosshair Color Blue"), crosshairColorBlue)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> crosshairColorBlue = newValue)
                .build());
        crosshair.addEntry(entryBuilder.startBooleanToggle(Text.literal("Use Custom Shape"), useCustomCrosshairShape)
                .setSaveConsumer(newValue -> useCustomCrosshairShape = newValue)
                .build());
        crosshair.addEntry(entryBuilder.startIntField(Text.literal("Crosshair Type"), crosshairType)
                .setMin(0).setMax(10)
                .setSaveConsumer(newValue -> crosshairType = newValue)
                .build());

        // Глобальная настройка скорости RGB
        ConfigCategory rgb = builder.getOrCreateCategory(Text.literal("RGB Settings"));
        rgb.addEntry(entryBuilder.startFloatField(Text.literal("RGB Speed"), rgbSpeed)
                .setMin(0.1f).setMax(5.0f)
                .setSaveConsumer(newValue -> rgbSpeed = newValue)
                .build());

        // Категория частиц при ударе
        ConfigCategory particles = builder.getOrCreateCategory(Text.literal("Hit Particles"));
        particles.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable Hit Particles"), enableHitParticles)
                .setSaveConsumer(newValue -> enableHitParticles = newValue)
                .build());
        particles.addEntry(entryBuilder.startIntField(Text.literal("Particle Count"), particleCount)
                .setMin(1).setMax(20)
                .setSaveConsumer(newValue -> particleCount = newValue)
                .build());
        particles.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable RGB Particles"), enableRGBParticles)
                .setSaveConsumer(newValue -> enableRGBParticles = newValue)
                .build());
        particles.addEntry(entryBuilder.startFloatField(Text.literal("Particle Color Red"), particleColorRed)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> particleColorRed = newValue)
                .build());
        particles.addEntry(entryBuilder.startFloatField(Text.literal("Particle Color Green"), particleColorGreen)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> particleColorGreen = newValue)
                .build());
        particles.addEntry(entryBuilder.startFloatField(Text.literal("Particle Color Blue"), particleColorBlue)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> particleColorBlue = newValue)
                .build());

        // Категория HUD
        ConfigCategory hud = builder.getOrCreateCategory(Text.literal("HUD"));
        hud.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable Armor HUD"), enableArmorHud)
                .setSaveConsumer(newValue -> enableArmorHud = newValue)
                .build());
        hud.addEntry(entryBuilder.startFloatField(Text.literal("Armor HUD Color Red"), armorHudColorRed)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> armorHudColorRed = newValue)
                .build());
        hud.addEntry(entryBuilder.startFloatField(Text.literal("Armor HUD Color Green"), armorHudColorGreen)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> armorHudColorGreen = newValue)
                .build());
        hud.addEntry(entryBuilder.startFloatField(Text.literal("Armor HUD Color Blue"), armorHudColorBlue)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> armorHudColorBlue = newValue)
                .build());
        hud.addEntry(entryBuilder.startFloatField(Text.literal("Armor HUD Alpha"), armorHudAlpha)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> armorHudAlpha = newValue)
                .build());

        hud.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable Potions HUD"), enablePotionsHud)
                .setSaveConsumer(newValue -> enablePotionsHud = newValue)
                .build());
        hud.addEntry(entryBuilder.startFloatField(Text.literal("Potions HUD Color Red"), potionsHudColorRed)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> potionsHudColorRed = newValue)
                .build());
        hud.addEntry(entryBuilder.startFloatField(Text.literal("Potions HUD Color Green"), potionsHudColorGreen)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> potionsHudColorGreen = newValue)
                .build());
        hud.addEntry(entryBuilder.startFloatField(Text.literal("Potions HUD Color Blue"), potionsHudColorBlue)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> potionsHudColorBlue = newValue)
                .build());
        hud.addEntry(entryBuilder.startFloatField(Text.literal("Potions HUD Alpha"), potionsHudAlpha)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> potionsHudAlpha = newValue)
                .build());

        hud.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable Target HUD"), enableTargetHud)
                .setSaveConsumer(newValue -> enableTargetHud = newValue)
                .build());
        hud.addEntry(entryBuilder.startFloatField(Text.literal("Target HUD Color Red"), targetHudColorRed)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> targetHudColorRed = newValue)
                .build());
        hud.addEntry(entryBuilder.startFloatField(Text.literal("Target HUD Color Green"), targetHudColorGreen)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> targetHudColorGreen = newValue)
                .build());
        hud.addEntry(entryBuilder.startFloatField(Text.literal("Target HUD Color Blue"), targetHudColorBlue)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> targetHudColorBlue = newValue)
                .build());
        hud.addEntry(entryBuilder.startFloatField(Text.literal("Target HUD Alpha"), targetHudAlpha)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> targetHudAlpha = newValue)
                .build());

        // Категория эффектов (Effectify)
        ConfigCategory effects = builder.getOrCreateCategory(Text.literal("Effects"));
        effects.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable Trail"), trailEnabled)
                .setSaveConsumer(newValue -> trailEnabled = newValue)
                .build());
        effects.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable RGB"), rgbEnabled)
                .setSaveConsumer(newValue -> rgbEnabled = newValue)
                .build());
        effects.addEntry(entryBuilder.startFloatField(Text.literal("Trail Color Red"), trailColorRed)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> trailColorRed = newValue)
                .build());
        effects.addEntry(entryBuilder.startFloatField(Text.literal("Trail Color Green"), trailColorGreen)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> trailColorGreen = newValue)
                .build());
        effects.addEntry(entryBuilder.startFloatField(Text.literal("Trail Color Blue"), trailColorBlue)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> trailColorBlue = newValue)
                .build());

        // Категория видимости сквозь блоки
        ConfigCategory seeThrough = builder.getOrCreateCategory(Text.literal("See Through"));
        seeThrough.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable See Through Blocks"), seeThroughBlocks)
                .setSaveConsumer(newValue -> seeThroughBlocks = newValue)
                .build());

        // Категория времени суток и неба
        ConfigCategory sky = builder.getOrCreateCategory(Text.literal("Sky"));
        sky.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable Custom Time"), customTimeEnabled)
                .setSaveConsumer(newValue -> customTimeEnabled = newValue)
                .build());
        sky.addEntry(entryBuilder.startIntSlider(Text.literal("Custom Time (Ticks)"), customTime, 0, 24000)
                .setSaveConsumer(newValue -> customTime = newValue)
                .build());
        sky.addEntry(entryBuilder.startFloatField(Text.literal("Sky Tint Red"), skyTintRed)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> skyTintRed = newValue)
                .build());
        sky.addEntry(entryBuilder.startFloatField(Text.literal("Sky Tint Green"), skyTintGreen)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> skyTintGreen = newValue)
                .build());
        sky.addEntry(entryBuilder.startFloatField(Text.literal("Sky Tint Blue"), skyTintBlue)
                .setMin(0.0f).setMax(1.0f)
                .setSaveConsumer(newValue -> skyTintBlue = newValue)
                .build());

        // Присваиваем экран конфигурации
        configScreen = builder.build();
    }

    // Метод для открытия конкретной категории конфигурации
    public static void openConfigScreen(String category) {
        Config.init();
        // Простая реализация: открывает основной экран
        net.minecraft.client.MinecraftClient.getInstance().setScreen(configScreen);
    }
}