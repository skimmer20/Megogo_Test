package utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author Yurii Pavliuk
 */
public class DateTimeUtils {

    public static Instant getCurrentInstant() {
        return Instant.now();
    }

    public static Instant fromEpochSecond(long epochSecond) {
        return Instant.ofEpochSecond(epochSecond);
    }

    public static long getCurrentEpochSecond() {
        return getCurrentInstant().getEpochSecond();
    }

    public static boolean isTimeDifferenceWithinThreshold(Instant time1, Instant time2, long thresholdInSeconds) {
        long differenceInSeconds = ChronoUnit.SECONDS.between(time1, time2);
        return Math.abs(differenceInSeconds) < thresholdInSeconds;
    }

    public static boolean isWithinTimeRange(long timestamp, long start, long end) {
        return timestamp >= start && timestamp <= end;
    }

    public static boolean isProgramInCurrentRange(long startTimestamp, long endTimestamp) {
        long currentTimestamp = getCurrentEpochSecond();
        return isWithinTimeRange(currentTimestamp, startTimestamp, endTimestamp);
    }
}
