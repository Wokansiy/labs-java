package ua.sports;
import ua.util.ValidationHelper;
import ua.util.Utils;
import java.util.Objects;
public abstract class Person{
    protected String firstName;
    protected String lastName;
    protected Person(String f,String l){
        this.firstName=ValidationHelper.requireNonBlank(f,"f");
        this.lastName=ValidationHelper.requireNonBlank(l,"l");
    }
    public String getFirstName(){return firstName;}
    public void setFirstName(String f){this.firstName=ValidationHelper.requireNonBlank(f,"f");}
    public String getLastName(){return lastName;}
    public void setLastName(String l){this.lastName=ValidationHelper.requireNonBlank(l,"l");}
    protected String getFullNameProtected(){return Utils.formatFullName(firstName,lastName);}
    public String toString(){return getFullNameProtected();}
    public boolean equals(Object o){if(this==o)return true; if(!(o instanceof Person p))return false;return Objects.equals(firstName,p.firstName)&&Objects.equals(lastName,p.lastName);}
    public int hashCode(){return Objects.hash(firstName,lastName);}
}