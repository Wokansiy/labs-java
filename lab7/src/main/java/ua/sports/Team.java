package ua.sports;
import ua.util.ValidationHelper;
import java.util.List;
public class Team{
    private String name;
    private String sport;
    private Coach coach;
    private java.util.List<Player> players;
    public Team(){}
    public Team(String name,String sport,Coach coach,List<Player> players){
        this.name=ValidationHelper.requireNonBlank(name,"name");
        this.sport=ValidationHelper.requireNonBlank(sport,"sport");
        this.coach=ValidationHelper.requireNonNull(coach,"coach");
        java.util.List<Player> safe=players==null?java.util.List.of():players;
        this.players=java.util.List.copyOf(safe);
    }
    public String getName(){return name;}
    public void setName(String v){this.name=ValidationHelper.requireNonBlank(v,"name");}
    public String getSport(){return sport;}
    public void setSport(String v){this.sport=ValidationHelper.requireNonBlank(v,"sport");}
    public Coach getCoach(){return coach;}
    public void setCoach(Coach c){this.coach=ValidationHelper.requireNonNull(c,"coach");}
    public java.util.List<Player> getPlayers(){return players;}
    public void setPlayers(java.util.List<Player> list){
        java.util.List<Player> safe=list==null?java.util.List.of():list;
        this.players=java.util.List.copyOf(safe);
    }
    public String summary(){return "Team "+name+" ("+sport+"), coach: "+coach.fullName()+", players: "+players.size();}
}
