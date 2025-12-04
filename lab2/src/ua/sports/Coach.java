package ua.sports;
import ua.util.ValidationHelper;
public record Coach(String firstName,String lastName,CoachRole role){
    public Coach{
        firstName=ValidationHelper.requireNonBlank(firstName,"firstName");
        lastName=ValidationHelper.requireNonBlank(lastName,"lastName");
        role=ValidationHelper.requireNonNull(role,"role");
    }
    public String fullName(){return firstName+" "+lastName;}
    public static Coach headCoach(String f,String l){return new Coach(f,l,CoachRole.HEAD_COACH);}
}