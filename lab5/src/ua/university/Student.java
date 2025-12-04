package ua.university;
import ua.util.ValidationHelper;
import java.util.Comparator;
import java.util.Objects;
public class Student implements Comparable<Student>{
    private final int id;
    private final String firstName;
    private final String lastName;
    private final double averageGrade;
    public static final Comparator<Student> BY_LAST_NAME=
            Comparator.comparing(Student::getLastName, String.CASE_INSENSITIVE_ORDER);
    public static final Comparator<Student> BY_GRADE_DESC=
            Comparator.comparingDouble(Student::getAverageGrade).reversed();
    public Student(int id,String f,String l,double g){
        this.id=ValidationHelper.requireInRange(id,1,Integer.MAX_VALUE,"id");
        this.firstName=ValidationHelper.requireNonBlank(f,"firstName");
        this.lastName=ValidationHelper.requireNonBlank(l,"lastName");
        this.averageGrade=g;
    }
    public int getId(){return id;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public double getAverageGrade(){return averageGrade;}
    public int compareTo(Student o){
        return Integer.compare(this.id,o.id);
    }
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