package ua.sports;

import ua.util.ValidationHelper;

import java.util.Objects;

public class Player extends Person {

    private String position;
    private int number;

    public Player(String firstName, String lastName, String position, int number) {
        super(firstName, lastName);
        setPosition(position);
        setNumber(number);
    }

    public static Player of(String firstName, String lastName, String position, int number) {
        return new Player(firstName, lastName, position, number);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = ValidationHelper.requireNonBlank(position, "position");
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = ValidationHelper.requireInRange(number, 1, 99, "number");
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + getFullNameProtected() + ''' +
                ", position='" + position + ''' +
                ", number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        if (!super.equals(o)) return false;
        return number == player.number &&
                Objects.equals(position, player.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position, number);
    }
}
