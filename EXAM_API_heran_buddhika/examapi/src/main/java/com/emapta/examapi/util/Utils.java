package com.emapta.examapi.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public final class Utils {

    private Utils() {
    }

    public static String formatCurrentDate() {
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd h:mma z")
                .toFormatter()
                .withZone(ZoneId.of("UTC"));
        return ZonedDateTime.now().format(dtf).replace("AM", "am").replace("PM", "pm");
    }
}
