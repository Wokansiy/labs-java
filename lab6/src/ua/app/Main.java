package ua.app;
import ua.repository.*;
import ua.sports.*;
import ua.university.*;
import ua.util.LogUtil;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class Main{
    private static final Logger LOG=LogUtil.createLogger(Main.class);
    public static void main(String[]args){
        StudentRepository studentRepo=new StudentRepository();
        GroupRepository groupRepo=new GroupRepository();
        PlayerRepository playerRepo=new PlayerRepository();
        TeamRepository teamRepo=new TeamRepository();

        Student s1=new Student(1,"Vlad","Marusiak",93.5);
        Student s2=new Student(2,"Vasyl","Kosovan",88.0);
        Student s3=new Student(3,"Ruslan","Biloskurskyi",91.2);
        studentRepo.add(s1);
        studentRepo.add(s2);
        studentRepo.add(s3);

        Group g1=new Group("CS-11",1, List.of(s1,s2));
        Group g2=new Group("CS-21",2, List.of(s3));
        groupRepo.add(g1);
        groupRepo.add(g2);

        Player p1=new Player("Сергій","Коба",PlayerPosition.FORWARD,23);
        Player p2=new Player("Юрій","Луцюк",PlayerPosition.MIDFIELDER,33);
        Player p3=new Player("Дмитро","Федорцов",PlayerPosition.DEFENDER,91);
        playerRepo.add(p1);
        playerRepo.add(p2);
        playerRepo.add(p3);

        Coach c1=new Coach("Віталій","Марусяк",CoachRole.HEAD_COACH);
        Team t1=new Team("ЧНУ","Basketball",c1,List.of(p1,p2,p3));
        teamRepo.add(t1);

        LOG.info("Students with lastName 'Marusiak': "+studentRepo.findByLastName("Marusiak"));
        LOG.info("Students with grade in [90,100]: "+studentRepo.findByGradeRange(90,100));
        LOG.info("Average grade: "+studentRepo.averageGrade());

        LOG.info("Groups on course 1: "+groupRepo.findByCourse(1));
        LOG.info("Groups with size in [1,2]: "+groupRepo.findBySizeRange(1,2));
        LOG.info("Students in group CS-11: "+groupRepo.findStudentsInGroup("CS-11"));

        LOG.info("Players FORWARD: "+playerRepo.findByPosition(PlayerPosition.FORWARD));
        LOG.info("Players with number in [20,40]: "+playerRepo.findByNumberRange(20,40));

        LOG.info("Basketball teams: "+teamRepo.findBySport("Basketball"));
        LOG.info("Total players in all teams: "+teamRepo.totalPlayersCount());

        List<Integer> gradesRounded=studentRepo.stream()
                .map(s->(int)Math.round(s.getAverageGrade()))
                .collect(Collectors.toList());
        LOG.info("Rounded grades via collect: "+gradesRounded);
        studentRepo.stream().forEach(s->LOG.info("forEach student: "+s));

        int sumNumbers=playerRepo.stream()
                .map(Player::getNumber)
                .reduce(0,(a,b)->a+b);
        LOG.info("Sum of player numbers via reduce: "+sumNumbers);

        compareStreamPerformance();
    }
    private static void compareStreamPerformance(){
        List<Integer> data=IntStream.rangeClosed(1,1_000_000).boxed().collect(Collectors.toList());
        long start1=System.currentTimeMillis();
        long sum1=data.stream().mapToLong(Integer::longValue).sum();
        long time1=System.currentTimeMillis()-start1;

        long start2=System.currentTimeMillis();
        long sum2=data.parallelStream().mapToLong(Integer::longValue).sum();
        long time2=System.currentTimeMillis()-start2;

        LOG.info("stream sum="+sum1+" time="+time1+"ms");
        LOG.info("parallelStream sum="+sum2+" time="+time2+"ms");
    }
}