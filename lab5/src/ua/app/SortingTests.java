package ua.app;
import ua.repository.*;
import ua.sports.*;
import ua.university.*;
import ua.util.LogUtil;
import java.util.*;
import java.util.logging.Logger;
public class SortingTests{
    private static final Logger LOG=LogUtil.createLogger(SortingTests.class);
    public static void main(String[]args){
        testStudentSorting();
        testGroupSorting();
        testRepositorySortByIdentity();
    }
    private static void testStudentSorting(){
        StudentRepository repo=new StudentRepository();
        Student s1=new Student(2,"B","B",80.0);
        Student s2=new Student(1,"A","A",90.0);
        repo.add(s1);
        repo.add(s2);
        List<Student> byId=repo.sortByIdentity("asc");
        if(byId.get(0).getId()==1) LOG.info("testStudentSorting sortByIdentity OK");
        else LOG.warning("testStudentSorting sortByIdentity FAIL");
        List<Student> byGrade=repo.sortByGradeDesc();
        if(byGrade.get(0).getAverageGrade()==90.0) LOG.info("testStudentSorting grade OK");
        else LOG.warning("testStudentSorting grade FAIL");
    }
    private static void testGroupSorting(){
        GroupRepository repo=new GroupRepository();
        Student s=new Student(1,"A","A",90.0);
        Group g1=new Group("G2",2, List.of(s,s));
        Group g2=new Group("G1",1, List.of(s));
        repo.add(g1);
        repo.add(g2);
        List<Group> byCourse=repo.sortByCourseAsc();
        if(byCourse.get(0).getCourse()==1) LOG.info("testGroupSorting course OK");
        else LOG.warning("testGroupSorting course FAIL");
        List<Group> bySize=repo.sortBySizeDesc();
        if(bySize.get(0).getStudents().size()==2) LOG.info("testGroupSorting size OK");
        else LOG.warning("testGroupSorting size FAIL");
    }
    private static void testRepositorySortByIdentity(){
        GenericRepository<Player> repo=new GenericRepository<>(p->String.valueOf(p.getNumber()));
        Player p1=new Player("A","A",PlayerPosition.FORWARD,20);
        Player p2=new Player("B","B",PlayerPosition.DEFENDER,10);
        repo.add(p1);
        repo.add(p2);
        List<Player> asc=repo.sortByIdentity("asc");
        if(asc.get(0).getNumber()==10) LOG.info("testRepositorySortByIdentity asc OK");
        else LOG.warning("testRepositorySortByIdentity asc FAIL");
        List<Player> desc=repo.sortByIdentity("desc");
        if(desc.get(0).getNumber()==20) LOG.info("testRepositorySortByIdentity desc OK");
        else LOG.warning("testRepositorySortByIdentity desc FAIL");
    }
}