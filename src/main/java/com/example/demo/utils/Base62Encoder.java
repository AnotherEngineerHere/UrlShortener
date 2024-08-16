package com.example.demo.utils;

public class Base62Encoder {

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encode(long timestamp) {
        StringBuilder shortUrl = new StringBuilder();
        while (timestamp > 0) {
            shortUrl.append(BASE62.charAt((int) (timestamp % 62)));
            timestamp /= 62;
        }
        return shortUrl.reverse().toString();
    }

    public static String generateShortCode() {
        long timestamp = System.currentTimeMillis();
        return encode(timestamp);
    }
}