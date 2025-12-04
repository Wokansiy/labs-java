package ua.university;
import ua.util.ValidationHelper;
import java.util.List;
import java.util.Objects;
public class Group{
    private String name;
    private int course;
    private java.util.List<Student> students;
    public Group(){}
    public Group(String name,int course,List<Student> students){
        this.name=ValidationHelper.requireNonBlank(name,"name");
        this.course=ValidationHelper.requireInRange(course,1,6,"course");
        java.util.List<Student> safe=students==null?java.util.List.of():students;
        this.students=java.util.List.copyOf(safe);
    }
    public String getName(){return name;}
    public void setName(String v){this.name=ValidationHelper.requireNonBlank(v,"name");}
    public int getCourse(){return course;}
    public void setCourse(int c){this.course=ValidationHelper.requireInRange(c,1,6,"course");}
    public java.util.List<Student> getStudents(){return students;}
    public void setStudents(java.util.List<Student> list){
        java.util.List<Student> safe=list==null?java.util.List.of():list;
        this.students=java.util.List.copyOf(safe);
    }
    public String toString(){return "Group{name='"+name+"', course="+course+", size="+students.size()+"}";}
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof Group g)) return false;
        return course==g.course
                &&Objects.equals(name,g.name)
                &&Objects.equals(students,g.students);
    }
    public int hashCode(){return Objects.hash(name,course,students);}
}
