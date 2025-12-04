package ua.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Utils() {
    }

    public static String formatFullName(String firstName, String lastName) {
        String f = ValidationHelper.requireNonBlank(firstName, "firstName");
        String l = ValidationHelper.requireNonBlank(lastName, "lastName");
        return f + " " + l;
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        ValidationHelper.requireNonNull(dateTime, "dateTime");
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
