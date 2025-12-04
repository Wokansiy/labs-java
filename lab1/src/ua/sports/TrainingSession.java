package ua.sports;

import ua.util.ValidationHelper;
import ua.util.Utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class TrainingSession {

    private final LocalDateTime sessionDateTime;
    private final Duration duration;
    private final Team team;

    public TrainingSession(LocalDateTime sessionDateTime, Duration duration, Team team) {
        this.sessionDateTime =
                ValidationHelper.requireFutureOrNow(sessionDateTime, "sessionDateTime");
        this.duration =
                ValidationHelper.requirePositiveDuration(duration, "duration");
        this.team = ValidationHelper.requireNonNull(team, "team");
    }

    public static TrainingSession of(LocalDateTime dateTime, Duration duration, Team team) {
        return new TrainingSession(dateTime, duration, team);
    }

    public LocalDateTime getSessionDateTime() {
        return sessionDateTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "TrainingSession{" +
                "dateTime=" + Utils.formatDateTime(sessionDateTime) +
                ", duration=" + duration.toMinutes() + " minutes" +
                ", team=" + team.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainingSession that)) return false;
        return Objects.equals(sessionDateTime, that.sessionDateTime)
                && Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionDateTime, team);
    }
}
