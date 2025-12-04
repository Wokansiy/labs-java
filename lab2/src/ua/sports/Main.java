package ua.sports;
import java.util.List;
public class Main{
    public static void main(String[]a){
        Coach c=Coach.headCoach("John","Smith");
        Player p1=Player.of("Mike","Jordan",PlayerPosition.FORWARD,23);
        Player p2=Player.of("Scottie","Pippen",PlayerPosition.MIDFIELDER,33);
        Player p3=Player.of("Dennis","Rodman",PlayerPosition.DEFENDER,91);
        Team t=new Team("Chicago Bulls","Basketball",c,List.of(p1,p2));
        Team t2=t.withAddedPlayer(p3);
        System.out.println(t.summary());
        System.out.println(t2.summary());
        System.out.println(RoleDescriptions.describePlayerPosition(p1.getPosition()));
        System.out.println(RoleDescriptions.describeCoachRole(c.role()));
    }
}