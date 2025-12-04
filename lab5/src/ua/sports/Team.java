package ua.sports;
import ua.util.ValidationHelper;
import java.util.Comparator;
import java.util.List;
public record Team(String name,String sport,Coach coach,List<Player> players) implements Comparable<Team>{
    public static final Comparator<Team> BY_SIZE_DESC=
            Comparator.comparingInt((Team t)->t.players().size()).reversed();
    public Team{
        name=ValidationHelper.requireNonBlank(name,"name");
        sport=ValidationHelper.requireNonBlank(sport,"sport");
        coach=ValidationHelper.requireNonNull(coach,"coach");
        List<Player> safe=players==null?List.of():players;
        players=List.copyOf(safe);
    }
    public String summary(){return "Team "+name+" ("+sport+"), coach: "+coach.fullName()+", players: "+players.size();}
    public Team withAddedPlayer(Player p){
        java.util.List<Player> n=new java.util.ArrayList<>(players);
        n.add(p);
        return new Team(name,sport,coach,n);
    }
    public int compareTo(Team o){
        return this.name.compareToIgnoreCase(o.name);
    }
}