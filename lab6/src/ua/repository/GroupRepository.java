package ua.repository;
import ua.university.Group;
import ua.university.Student;
import ua.util.LogUtil;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
public class GroupRepository extends GenericRepository<Group>{
    private final Logger log=LogUtil.createLogger(GroupRepository.class);
    public GroupRepository(){
        super(Group::getName);
    }
    public List<Group> findByCourse(int course){
        List<Group> result=stream()
                .filter(g->g.getCourse()==course)
                .collect(Collectors.toList());
        log.info("findByCourse("+course+") -> "+result.size());
        return result;
    }
    public List<Group> findBySizeRange(int min,int max){
        List<Group> result=stream()
                .filter(g->{int s=g.getStudents().size(); return s>=min && s<=max;})
                .collect(Collectors.toList());
        log.info("findBySizeRange("+min+","+max+") -> "+result.size());
        return result;
    }
    public List<Student> findStudentsInGroup(String groupName){
        List<Student> result=stream()
                .filter(g->g.getName().equalsIgnoreCase(groupName))
                .flatMap(g->g.getStudents().stream())
                .collect(Collectors.toList());
        log.info("findStudentsInGroup('"+groupName+"') -> "+result.size());
        return result;
    }
}