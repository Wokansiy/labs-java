package ua.app;
import ua.repository.GenericRepository;
import ua.sports.*;
import ua.util.LogUtil;
import java.util.List;
import java.util.logging.Logger;
public class Main{
    private static final Logger LOG=LogUtil.createLogger(Main.class);
    public static void main(String[]args){
        GenericRepository<Player> playerRepo=new GenericRepository<>(p->String.valueOf(p.getNumber()));
        GenericRepository<Coach> coachRepo=new GenericRepository<>(c->c.lastName()+"_"+c.firstName());
        GenericRepository<Team> teamRepo=new GenericRepository<>(Team::name);

        Player p1=new Player("Vlad","Marusiak",PlayerPosition.FORWARD,19);
        Player p2=new Player("Vasyl","Kosovan",PlayerPosition.DEFENDER,40);
        Player p3=new Player("Ruslan","Biloskurskyi",PlayerPosition.MIDFIELDER,50);
        Player pDup=new Player("Copy","Marusiak",PlayerPosition.FORWARD,19);

        Coach c1=new Coach("Phil","Jackson",CoachRole.HEAD_COACH);
        Coach c2=new Coach("John","Doe",CoachRole.ASSISTANT_COACH);
        Coach cDup=new Coach("Fake","Doe",CoachRole.FITNESS_COACH);

        Team team=new Team("Bulls","Basketball",c1, List.of(p1,p2,p3));

        playerRepo.add(p1);
        playerRepo.add(p2);
        playerRepo.add(p3);
        playerRepo.add(pDup);

        coachRepo.add(c1);
        coachRepo.add(c2);
        coachRepo.add(cDup);

        teamRepo.add(team);

        LOG.info("All players: "+playerRepo.getAll());
        LOG.info("Player with number 19: "+playerRepo.findByIdentity("19"));
        LOG.info("Coach Doe_John: "+coachRepo.findByIdentity("Doe_John"));

        playerRepo.removeByIdentity("40");
        LOG.info("Players after removal of 40: "+playerRepo.getAll());
    }
}