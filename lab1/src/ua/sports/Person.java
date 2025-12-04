package ua.sports;

import ua.util.ValidationHelper;
import ua.util.Utils;

import java.util.Objects;

public abstract class Person {

    protected String firstName;
    protected String lastName;

    protected Person(String firstName, String lastName) {
        this.firstName = ValidationHelper.requireNonBlank(firstName, "firstName");
        this.lastName = ValidationHelper.requireNonBlank(lastName, "lastName");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = ValidationHelper.requireNonBlank(firstName, "firstName");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = ValidationHelper.requireNonBlank(lastName, "lastName");
    }

    protected String getFullNameProtected() {
        return Utils.formatFullName(firstName, lastName);
    }

    @Override
    public String toString() {
        return getFullNameProtected();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
