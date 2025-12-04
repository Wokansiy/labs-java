package ua.app;
import ua.repository.*;
import ua.sports.*;
import ua.university.*;
import ua.util.LogUtil;
import java.util.*;
import java.util.logging.Logger;
public class StreamTests{
    private static final Logger LOG=LogUtil.createLogger(StreamTests.class);
    public static void main(String[]args){
        testStudentRepository();
        testGroupRepository();
        testPlayerRepository();
        testTeamRepository();
    }
    private static void testStudentRepository(){
        StudentRepository repo=new StudentRepository();
        Student s1=new Student(1,"A","Last",80.0);
        Student s2=new Student(2,"B","Last",90.0);
        Student s3=new Student(3,"C","Other",70.0);
        repo.add(s1);repo.add(s2);repo.add(s3);
        if(repo.findByLastName("Last").size()==2) LOG.info("testStudentRepository lastName OK");
        else LOG.warning("testStudentRepository lastName FAIL");
        if(repo.findByGradeRange(75,95).size()==2) LOG.info("testStudentRepository gradeRange OK");
        else LOG.warning("testStudentRepository gradeRange FAIL");
    }
    private static void testGroupRepository(){
        GroupRepository repo=new GroupRepository();
        Student s=new Student(1,"A","A",80.0);
        Group g1=new Group("G1",1, List.of(s,s));
        Group g2=new Group("G2",2, List.of(s));
        repo.add(g1);repo.add(g2);
        if(repo.findByCourse(1).size()==1) LOG.info("testGroupRepository course OK");
        else LOG.warning("testGroupRepository course FAIL");
        if(repo.findBySizeRange(2,10).size()==1) LOG.info("testGroupRepository sizeRange OK");
        else LOG.warning("testGroupRepository sizeRange FAIL");
        if(repo.findStudentsInGroup("G1").size()==2) LOG.info("testGroupRepository studentsInGroup OK");
        else LOG.warning("testGroupRepository studentsInGroup FAIL");
    }
    private static void testPlayerRepository(){
        PlayerRepository repo=new PlayerRepository();
        Player p1=new Player("A","A",PlayerPosition.FORWARD,10);
        Player p2=new Player("B","B",PlayerPosition.DEFENDER,20);
        Player p3=new Player("C","C",PlayerPosition.FORWARD,30);
        repo.add(p1);repo.add(p2);repo.add(p3);
        if(repo.findByPosition(PlayerPosition.FORWARD).size()==2) LOG.info("testPlayerRepository position OK");
        else LOG.warning("testPlayerRepository position FAIL");
        if(repo.findByNumberRange(15,35).size()==2) LOG.info("testPlayerRepository numberRange OK");
        else LOG.warning("testPlayerRepository numberRange FAIL");
    }
    private static void testTeamRepository(){
        TeamRepository repo=new TeamRepository();
        Player p=new Player("A","A",PlayerPosition.FORWARD,10);
        Player p2=new Player("B","B",PlayerPosition.DEFENDER,20);
        Coach c=new Coach("Coach","One",CoachRole.HEAD_COACH);
        Team t1=new Team("Team1","Football",c,List.of(p));
        Team t2=new Team("Team2","Football",c,List.of(p,p2));
        repo.add(t1);repo.add(t2);
        if(repo.findBySport("Football").size()==2) LOG.info("testTeamRepository sport OK");
        else LOG.warning("testTeamRepository sport FAIL");
        if(repo.totalPlayersCount()==3) LOG.info("testTeamRepository totalPlayersCount OK");
        else LOG.warning("testTeamRepository totalPlayersCount FAIL");
    }
}