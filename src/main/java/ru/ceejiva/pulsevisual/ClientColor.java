package ru.ceejiva.pulsevisual;

import org.joml.Vector3f;

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
        Vector3f rgb = hsbToRgbVector(hue / 360.0f, 1.0f, 1.0f);
        return phase == 0 ? rgb.x : (phase == 120 ? rgb.y : rgb.z);
    }

    private static Vector3f hsbToRgbVector(float hue, float saturation, float brightness) {
        int rgb = java.awt.Color.HSBtoRGB(hue, saturation, brightness);
        float r = ((rgb >> 16) & 0xFF) / 255.0f;
        float g = ((rgb >> 8) & 0xFF) / 255.0f;
        float b = (rgb & 0xFF) / 255.0f;
        return new Vector3f(r, g, b);
    }

    private static int hsbToRgb(float hue, float saturation, float brightness, float alpha) {
        int rgb = java.awt.Color.HSBtoRGB(hue / 360.0f, saturation, brightness);
        return (rgb & 0x00FFFFFF) | ((int)(alpha * 255) << 24);
    }
}