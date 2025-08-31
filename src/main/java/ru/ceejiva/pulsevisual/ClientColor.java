package ru.ceejiva.pulsevisual;

public class ClientColor {
    public static void init() {}

    public static int getColor(float red, float green, float blue, float alpha) {
        if (Config.rgbEnabled) {
            long time = System.currentTimeMillis();
            float hue = ((time % (long)(Config.rgbSpeed * 1000)) / (float)(Config.rgbSpeed * 1000)) * 360;
            return hsbToRgb(hue, 1.0f, 1.0f, alpha);
        }
        return ((int)(alpha * 255) << 24) | ((int)(red * 255) << 16) | ((int)(green * 255) << 8) | (int)(blue * 255);
    }

    public static float getRgbColorComponent(long time, float phase) {
        float hue = ((time % (long)(Config.rgbSpeed * 1000)) / (float)(Config.rgbSpeed * 1000)) * 360 + phase;
        return java.awt.Color.HSBtoRGB(hue / 360.0f, 1.0f, 1.0f) / 255.0f;
    }

    private static int hsbToRgb(float hue, float saturation, float brightness, float alpha) {
        int rgb = java.awt.Color.HSBtoRGB(hue / 360.0f, saturation, brightness);
        return (rgb & 0x00FFFFFF) | ((int)(alpha * 255) << 24);
    }
}