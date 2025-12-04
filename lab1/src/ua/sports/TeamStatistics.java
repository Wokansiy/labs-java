package ua.sports;

class TeamStatistics {

    static int countPlayers(Team team) {
        return team.getPlayers().size();
    }

    static double averageNumber(Team team) {
        if (team.getPlayers().isEmpty()) {
            return 0.0;
        }
        int sum = team.getPlayers().stream()
                .mapToInt(Player::getNumber)
                .sum();
        return (double) sum / team.getPlayers().size();
    }
}
