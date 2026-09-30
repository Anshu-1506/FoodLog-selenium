package com.foodlog.utils;

public final class Config {
    private Config() {}

    public static String baseUrl() {
        String v = System.getProperty("baseUrl");
        return (v == null || v.isBlank() || v.startsWith("${")) ? "http://localhost:5173" : v;
    }

    public static boolean headless() {
        String v = System.getProperty("headless");
        return v == null || v.isBlank() || v.startsWith("${") || Boolean.parseBoolean(v);
    }

    public static String uniqueEmail() {
        return "qa_" + System.currentTimeMillis() + "_" + (int) (Math.random() * 1000) + "@example.com";
    }
}