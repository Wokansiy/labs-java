package ua.sports;

import ua.util.Utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Створення об'єктів (конструктори та фабрики) ===");
        Coach coach = Coach.headCoach("John", "Smith");
        Player p1 = Player.of("Mike", "Jordan", "Forward", 23);
        Player p2 = new Player("Scottie", "Pippen", "Guard", 33);

        Team teamA = Team.create("Chicago Bulls", "Basketball", coach);
        teamA.addPlayer(p1);
        teamA.addPlayer(p2);

        System.out.println(teamA);

        System.out.println("
=== Використання Utils (форматування) ===");
        System.out.println("Повне ім'я гравця: " +
                Utils.formatFullName(p1.getFirstName(), p1.getLastName()));

        System.out.println("
=== Демонстрація валідації (успішні та неуспішні сценарії) ===");
        try {
            Player badPlayer = Player.of("   ", "NoName", "Goalkeeper", 1);
            System.out.println(badPlayer);
        } catch (IllegalArgumentException ex) {
            System.out.println("Помилка створення гравця: " + ex.getMessage());
        }

        try {
            p1.setNumber(0);
        } catch (IllegalArgumentException ex) {
            System.out.println("Помилка встановлення номера гравця: " + ex.getMessage());
        }

        System.out.println("
=== Match та TrainingSession ===");
        Team teamB = Team.create("Lakers", "Basketball",
                new Coach("Phil", "Jackson", "Head Coach"));
        Player p3 = Player.of("Kobe", "Bryant", "Guard", 8);
        teamB.addPlayer(p3);

        Match match = Match.schedule(
                teamA,
                teamB,
                LocalDateTime.now().plusDays(1)
        );

        System.out.println(match);
        match.setScore("102:99");
        System.out.println("Після встановлення рахунку: " + match);

        TrainingSession session = TrainingSession.of(
                LocalDateTime.now().plusHours(2),
                Duration.ofMinutes(90),
                teamA
        );
        System.out.println(session);

        System.out.println("
=== Доступ до protected ===");
        System.out.println("Ім'я гравця через protected поле: "
                + p1.firstName + " " + p1.lastName);

        System.out.println("
=== Доступ до package-private членів ===");
        teamA.addPlayerInternal(new Player("Dennis", "Rodman", "Forward", 91));
        System.out.println("Кількість гравців у команді A: " + TeamStatistics.countPlayers(teamA));
        System.out.println("Середній ігровий номер у команді A: " + TeamStatistics.averageNumber(teamA));
    }
}
