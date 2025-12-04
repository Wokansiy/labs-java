package ua.sports;

import ua.util.ValidationHelper;
import ua.util.Utils;

import java.time.LocalDateTime;
import java.util.Objects;

public class Match {

    private final Team homeTeam;
    private final Team awayTeam;
    private final LocalDateTime matchDateTime;
    private String score;

    public Match(Team homeTeam, Team awayTeam, LocalDateTime matchDateTime) {
        this.homeTeam = ValidationHelper.requireNonNull(homeTeam, "homeTeam");
        this.awayTeam = ValidationHelper.requireNonNull(awayTeam, "awayTeam");
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("homeTeam and awayTeam must be different");
        }
        this.matchDateTime =
                ValidationHelper.requireFutureOrNow(matchDateTime, "matchDateTime");
    }

    public static Match schedule(Team homeTeam, Team awayTeam, LocalDateTime dateTime) {
        return new Match(homeTeam, awayTeam, dateTime);
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public LocalDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        if (score == null) {
            this.score = null;
            return;
        }
        String trimmed = score.trim();
        if (!trimmed.matches("\d+\s*:\s*\d+")) {
            throw new IllegalArgumentException("Score must be in format 'X:Y'");
        }
        this.score = trimmed.replace(" ", "");
    }

    @Override
    public String toString() {
        return "Match{" +
                homeTeam.getName() + " vs " + awayTeam.getName() +
                ", dateTime=" + Utils.formatDateTime(matchDateTime) +
                ", score=" + (score == null ? "N/A" : score) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match match)) return false;
        return Objects.equals(homeTeam, match.homeTeam)
                && Objects.equals(awayTeam, match.awayTeam)
                && Objects.equals(matchDateTime, match.matchDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, matchDateTime);
    }
}
