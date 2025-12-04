package ua.university;
import ua.util.ValidationHelper;
import java.util.Comparator;
import java.util.List;
public class Group implements Comparable<Group>{
    private final String name;
    private final int course;
    private final List<Student> students;
    public static final Comparator<Group> BY_COURSE_ASC=
            Comparator.comparingInt(Group::getCourse);
    public static final Comparator<Group> BY_SIZE_DESC=
            Comparator.comparingInt((Group g)->g.getStudents().size()).reversed();
    public Group(String name,int course,List<Student> students){
        this.name=ValidationHelper.requireNonBlank(name,"name");
        this.course=ValidationHelper.requireInRange(course,1,6,"course");
        List<Student> safe=students==null?List.of():students;
        this.students=List.copyOf(safe);
    }
    public String getName(){return name;}
    public int getCourse(){return course;}
    public List<Student> getStudents(){return students;}
    public int compareTo(Group o){
        return this.name.compareToIgnoreCase(o.name);
    }
    public String toString(){return "Group{name='"+name+"', course="+course+", size="+students.size()+"}";}
}