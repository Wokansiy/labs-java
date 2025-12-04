package ua.university;
import ua.util.ValidationHelper;
import java.util.Objects;
public class Student{
    private int id;
    private String firstName;
    private String lastName;
    private double averageGrade;
    public Student(){}
    public Student(int id,String f,String l,double g){
        this.id=ValidationHelper.requireInRange(id,1,Integer.MAX_VALUE,"id");
        this.firstName=ValidationHelper.requireNonBlank(f,"firstName");
        this.lastName=ValidationHelper.requireNonBlank(l,"lastName");
        this.averageGrade=g;
    }
    public int getId(){return id;}
    public void setId(int v){this.id=ValidationHelper.requireInRange(v,1,Integer.MAX_VALUE,"id");}
    public String getFirstName(){return firstName;}
    public void setFirstName(String v){this.firstName=ValidationHelper.requireNonBlank(v,"firstName");}
    public String getLastName(){return lastName;}
    public void setLastName(String v){this.lastName=ValidationHelper.requireNonBlank(v,"lastName");}
    public double getAverageGrade(){return averageGrade;}
    public void setAverageGrade(double g){this.averageGrade=g;}
    public String toString(){return "Student{id="+id+", name='"+firstName+" "+lastName+"', grade="+averageGrade+"}";}
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof Student s)) return false;
        return id==s.id&&Double.compare(averageGrade,s.averageGrade)==0
                &&Objects.equals(firstName,s.firstName)
                &&Objects.equals(lastName,s.lastName);
    }
    public int hashCode(){return Objects.hash(id,firstName,lastName,averageGrade);}
}
