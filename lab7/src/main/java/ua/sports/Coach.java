package ua.sports;
import ua.util.ValidationHelper;
public class Coach{
    private String firstName;
    private String lastName;
    private CoachRole role;
    public Coach(){}
    public Coach(String firstName,String lastName,CoachRole role){
        this.firstName=ValidationHelper.requireNonBlank(firstName,"firstName");
        this.lastName=ValidationHelper.requireNonBlank(lastName,"lastName");
        this.role=ValidationHelper.requireNonNull(role,"role");
    }
    public String getFirstName(){return firstName;}
    public void setFirstName(String v){this.firstName=ValidationHelper.requireNonBlank(v,"firstName");}
    public String getLastName(){return lastName;}
    public void setLastName(String v){this.lastName=ValidationHelper.requireNonBlank(v,"lastName");}
    public CoachRole getRole(){return role;}
    public void setRole(CoachRole r){this.role=ValidationHelper.requireNonNull(r,"role");}
    public String fullName(){return firstName+" "+lastName;}
}
