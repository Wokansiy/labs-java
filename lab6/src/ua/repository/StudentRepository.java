package ua.repository;
import ua.university.Student;
import ua.util.LogUtil;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
public class StudentRepository extends GenericRepository<Student>{
    private final Logger log=LogUtil.createLogger(StudentRepository.class);
    public StudentRepository(){
        super(s->String.valueOf(s.getId()));
    }
    public List<Student> findByLastName(String lastName){
        List<Student> result=stream()
                .filter(s->s.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
        log.info("findByLastName('"+lastName+"') -> "+result.size());
        return result;
    }
    public List<Student> findByGradeRange(double min,double max){
        List<Student> result=stream()
                .filter(s->s.getAverageGrade()>=min && s.getAverageGrade()<=max)
                .collect(Collectors.toList());
        log.info("findByGradeRange("+min+","+max+") -> "+result.size());
        return result;
    }
    public double averageGrade(){
        double avg=stream()
                .mapToDouble(Student::getAverageGrade)
                .average()
                .orElse(0.0);
        log.info("averageGrade() -> "+avg);
        return avg;
    }
}