package com.core.fullstack_backend.Utils;

import java.util.UUID;

public class UUIDUtils {

    private UUIDUtils() {
        // Private constructor to prevent instantiation
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
