package ua.university;
import ua.util.ValidationHelper;
import java.util.List;
public class Group{
    private final String name;
    private final int course;
    private final List<Student> students;
    public Group(String name,int course,List<Student> students){
        this.name=ValidationHelper.requireNonBlank(name,"name");
        this.course=ValidationHelper.requireInRange(course,1,6,"course");
        List<Student> safe=students==null?List.of():students;
        this.students=List.copyOf(safe);
    }
    public String getName(){return name;}
    public int getCourse(){return course;}
    public List<Student> getStudents(){return students;}
    public String toString(){return "Group{name='"+name+"', course="+course+", size="+students.size()+"}";}
}