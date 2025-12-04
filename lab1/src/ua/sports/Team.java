package ua.sports;

import ua.util.ValidationHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Team {

    private String name;
    private String sport;
    private final Coach coach;
    private final List<Player> players = new ArrayList<>();

    public Team(String name, String sport, Coach coach) {
        this.name = ValidationHelper.requireNonBlank(name, "name");
        this.sport = ValidationHelper.requireNonBlank(sport, "sport");
        this.coach = ValidationHelper.requireNonNull(coach, "coach");
    }

    public static Team create(String name, String sport, Coach coach) {
        return new Team(name, sport, coach);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ValidationHelper.requireNonBlank(name, "name");
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = ValidationHelper.requireNonBlank(sport, "sport");
    }

    public Coach getCoach() {
        return coach;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void addPlayer(Player player) {
        ValidationHelper.requireNonNull(player, "player");
        addPlayerInternal(player);
    }

    void addPlayerInternal(Player player) {
        players.add(player);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + ''' +
                ", sport='" + sport + ''' +
                ", coach=" + coach +
                ", players=" + players +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team team)) return false;
        return Objects.equals(name, team.name)
                && Objects.equals(sport, team.sport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sport);
    }
}
