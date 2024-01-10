package com.example.back.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Datetime {
    private static final ZoneId KST_ZONE_ID = ZoneId.of("Asia/Seoul");

    public static LocalDateTime nowInKst() {
        return LocalDateTime.now(KST_ZONE_ID);
    }
}
