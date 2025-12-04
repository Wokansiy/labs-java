package ua.sports;
import ua.util.ValidationHelper;
import java.util.Comparator;
public record Coach(String firstName,String lastName,CoachRole role) implements Comparable<Coach>{
    public static final Comparator<Coach> BY_ROLE=Comparator.comparing(Coach::role);
    public Coach{
        firstName=ValidationHelper.requireNonBlank(firstName,"firstName");
        lastName=ValidationHelper.requireNonBlank(lastName,"lastName");
        role=ValidationHelper.requireNonNull(role,"role");
    }
    public String fullName(){return firstName+" "+lastName;}
    public static Coach headCoach(String f,String l){return new Coach(f,l,CoachRole.HEAD_COACH);}
    public int compareTo(Coach o){
        int c=this.lastName.compareToIgnoreCase(o.lastName);
        if(c!=0) return c;
        return this.firstName.compareToIgnoreCase(o.firstName);
    }
}