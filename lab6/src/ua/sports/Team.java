package ua.sports;
import ua.util.ValidationHelper;
import java.util.List;
public record Team(String name,String sport,Coach coach,List<Player> players){
    public Team{
        name=ValidationHelper.requireNonBlank(name,"name");
        sport=ValidationHelper.requireNonBlank(sport,"sport");
        coach=ValidationHelper.requireNonNull(coach,"coach");
        List<Player> safe=players==null?List.of():players;
        players=List.copyOf(safe);
    }
    public String summary(){return "Team "+name+" ("+sport+"), coach: "+coach.fullName()+", players: "+players.size();}
}