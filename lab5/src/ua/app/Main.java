package ua.app;
import ua.repository.*;
import ua.sports.*;
import ua.university.*;
import ua.util.LogUtil;
import java.util.*;
import java.util.logging.Logger;
public class Main{
    private static final Logger LOG=LogUtil.createLogger(Main.class);
    public static void main(String[]args){
        StudentRepository studentRepo=new StudentRepository();
        GroupRepository groupRepo=new GroupRepository();
        GenericRepository<Player> playerRepo=new GenericRepository<>(p->String.valueOf(p.getNumber()));

        Student s1=new Student(1,"Vlad","Marusiak",93.5);
        Student s2=new Student(2,"Vasyl","Kosovan",88.0);
        Student s3=new Student(3,"Ruslan","Biloskurskyi",91.2);
        Student sDup=new Student(1,"Duplicate","Student",70.0);

        studentRepo.add(s1);
        studentRepo.add(s2);
        studentRepo.add(s3);
        studentRepo.add(sDup);

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
        Coach c2=new Coach("Дмитро","Павлюк",CoachRole.ASSISTANT_COACH);
        Team team=new Team("ЧНУ","Basketball",c1,List.of(p1,p2,p3));

        LOG.info("Students sort by id (identity asc): "+studentRepo.sortByIdentity("asc"));
        LOG.info("Students sort by lastName: "+studentRepo.sortByLastName());
        LOG.info("Students sort by grade desc: "+studentRepo.sortByGradeDesc());

        LOG.info("Groups sort by name (identity desc): "+groupRepo.sortByIdentity("desc"));
        LOG.info("Groups sort by course: "+groupRepo.sortByCourseAsc());
        LOG.info("Groups sort by size desc: "+groupRepo.sortBySizeDesc());

        List<Player> playersByNumber=new ArrayList<>(playerRepo.getAll());
        playersByNumber.sort(Player.BY_NUMBER_ASC);
        LOG.info("Players by number: "+playersByNumber);

        List<Player> playersByPositionThenNumber=new ArrayList<>(playerRepo.getAll());
        playersByPositionThenNumber.sort(Player.BY_POSITION_THEN_NUMBER);
        LOG.info("Players by position then number: "+playersByPositionThenNumber);

        LOG.info("Team: "+team.summary());
        LOG.info("Coach c2: "+c2.fullName()+" role="+c2.role());
    }
}