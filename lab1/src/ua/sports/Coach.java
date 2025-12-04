package ua.sports;

import ua.util.ValidationHelper;

import java.util.Objects;

public class Coach extends Person {

    private String role;

    public Coach(String firstName, String lastName, String role) {
        super(firstName, lastName);
        setRole(role);
    }

    public static Coach headCoach(String firstName, String lastName) {
        return new Coach(firstName, lastName, "Head Coach");
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = ValidationHelper.requireNonBlank(role, "role");
    }

    @Override
    public String toString() {
        return "Coach{" +
                "name='" + getFullNameProtected() + ''' +
                ", role='" + role + ''' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coach coach)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(role, coach.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role);
    }
}
