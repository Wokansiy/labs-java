package ua.util;

import java.time.Duration;
import java.time.LocalDateTime;

class ValidationHelper {

    static String requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value.trim();
    }

    static <T> T requireNonNull(T value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null");
        }
        return value;
    }

    static int requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
        return value;
    }

    static int requireInRange(int value, int minInclusive, int maxInclusive, String fieldName) {
        if (value < minInclusive || value > maxInclusive) {
            throw new IllegalArgumentException(
                    fieldName + " must be between " + minInclusive + " and " + maxInclusive
            );
        }
        return value;
    }

    static LocalDateTime requireFutureOrNow(LocalDateTime dateTime, String fieldName) {
        requireNonNull(dateTime, fieldName);
        return dateTime;
    }

    static Duration requirePositiveDuration(Duration duration, String fieldName) {
        requireNonNull(duration, fieldName);
        if (duration.isZero() || duration.isNegative()) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
        return duration;
    }
}
