package ua.repository;
import ua.university.Group;
import ua.util.LogUtil;
import java.util.*;
import java.util.logging.Logger;
public class GroupRepository extends GenericRepository<Group>{
    private final Logger log=LogUtil.createLogger(GroupRepository.class);
    public GroupRepository(){
        super(Group::getName);
    }
    public List<Group> sortByCourseAsc(){
        List<Group> list=getAll();
        list.sort(Group.BY_COURSE_ASC);
        log.info("Sorted groups by course");
        return list;
    }
    public List<Group> sortBySizeDesc(){
        List<Group> list=getAll();
        list.sort(Group.BY_SIZE_DESC);
        log.info("Sorted groups by size desc");
        return list;
    }
}