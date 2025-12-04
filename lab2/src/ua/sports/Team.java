package ua.sports;
import ua.util.ValidationHelper;
import java.util.List;
public record Team(String name,String sport,Coach coach,List<Player> players){
    public Team{
        name=ValidationHelper.requireNonBlank(name,"name");
        sport=ValidationHelper.requireNonBlank(sport,"sport");
        coach=ValidationHelper.requireNonNull(coach,"coach");
        players=List.copyOf(players==null?List.of():players);
    }
    public String summary(){return "Team "+name+" ("+sport+"), coach: "+coach.fullName()+", players: "+players.size();}
    public Team withAddedPlayer(Player p){
        List<Player> n=new java.util.ArrayList<>(players);
        n.add(p);
        return new Team(name,sport,coach,n);
    }
}