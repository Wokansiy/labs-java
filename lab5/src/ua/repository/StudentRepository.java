package ua.repository;
import ua.university.Student;
import ua.util.LogUtil;
import java.util.*;
import java.util.logging.Logger;
public class StudentRepository extends GenericRepository<Student>{
    private final Logger log=LogUtil.createLogger(StudentRepository.class);
    public StudentRepository(){
        super(s->String.valueOf(s.getId()));
    }
    public List<Student> sortByLastName(){
        List<Student> list=getAll();
        list.sort(Student.BY_LAST_NAME);
        log.info("Sorted students by lastName");
        return list;
    }
    public List<Student> sortByGradeDesc(){
        List<Student> list=getAll();
        list.sort(Student.BY_GRADE_DESC);
        log.info("Sorted students by grade desc");
        return list;
    }
}